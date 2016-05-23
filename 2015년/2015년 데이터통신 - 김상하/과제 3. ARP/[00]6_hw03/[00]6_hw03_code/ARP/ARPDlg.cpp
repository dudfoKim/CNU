
// ARPDlg.cpp : implementation file
//

#include "stdafx.h"
#include "ARP.h"
#include "ARPDlg.h"
#include "afxdialogex.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CARPDlg dialog



CARPDlg::CARPDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CARPDlg::IDD, pParent), CBaseLayer("ARPDlg"), m_bSendReady(FALSE)
	, m_stSrcAdd(_T(""))
	, m_stDesAddr(_T(""))
	, m_proxyMacAddr(_T(""))
	, m_strGratuitousAddr(_T(""))
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);

	//  m_stSrcAdd = _T("");
	//}}AFX_DATA_INIT
	// Note that LoadIcon does not require a subsequent DestroyIcon in Win32
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);

	m_LayerMgr.AddLayer(new CEthernetLayer("Ethernet"));
	m_LayerMgr.AddLayer(new ARPLayer("ARP"));
	m_LayerMgr.AddLayer(new CIPLayer("Ip"));
	m_LayerMgr.AddLayer(new CNILayer("NI"));
	m_LayerMgr.AddLayer(this);


	//connet Layers
	m_LayerMgr.ConnectLayers("NI ( *Ethernet  ( *ARP ( *Ip ( *ARPDlg ) ) +Ip ) )");

	m_NI = (CNILayer*)m_LayerMgr.GetLayer("NI");
	m_ARP = (ARPLayer*)m_LayerMgr.GetLayer("ARP");
	m_IP = (CIPLayer*)m_LayerMgr.GetLayer("Ip");
	m_Ether = (CEthernetLayer*)m_LayerMgr.GetLayer("Ethernet");
}

void CARPDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_SRCMACADDR, m_stSrcAdd);
	DDX_Control(pDX, IDC_NIC, m_NICSet);
	DDX_Text(pDX, IDC_EDIT_DES_ADDR, m_stDesAddr);
	DDX_Control(pDX, IDC_EDIT_DES_ADDR, IPaddr);
	DDX_Control(pDX, IDC_EDIT_SRC_ADDR, m_srcIPAddr);
	DDX_Control(pDX, IDC_ARP_TABLE, m_lcARPTable);
	DDX_Control(pDX, IDC_PROXY_TABLE, m_lcProxyTable);
	DDX_Control(pDX, IDC_EDIT_PROXY_IP_ADDR, m_proxyIPAddr);
	DDX_Text(pDX, IDC_PROXY_MACADDR, m_proxyMacAddr);
	DDX_Text(pDX, IDC_GRATUITOUS_ADDR, m_strGratuitousAddr);
}

BEGIN_MESSAGE_MAP(CARPDlg, CDialogEx)
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
ON_BN_CLICKED(IDC_BUTTON_ADDR_SET, &CARPDlg::OnClickedButtonAddrSet)
ON_BN_CLICKED(IDC_BUTTON_SEND, &CARPDlg::OnClickedButtonSend)
ON_NOTIFY(IPN_FIELDCHANGED, IDC_EDIT_DES_ADDR, &CARPDlg::OnIpnFieldchangedEditDesAddr)
ON_BN_CLICKED(IDC_BUTTON_REFRESH, &CARPDlg::OnBnClickedButtonRefresh)
ON_BN_CLICKED(IDC_PROXY_ADD_BUTTON, &CARPDlg::OnBnClickedProxyAddButton)
ON_BN_CLICKED(IDC_PROXY_DELETE_BUTTON, &CARPDlg::OnBnClickedProxyDeleteButton)
ON_BN_CLICKED(IDC_GRATUITOUS_SEND, &CARPDlg::OnBnClickedGratuitousSend)
END_MESSAGE_MAP()


// CARPDlg message handlers

BOOL CARPDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// Set the icon for this dialog.  The framework does this automatically
	//  when the application's main window is not a dialog
	SetIcon(m_hIcon, TRUE);			// Set big icon
	SetIcon(m_hIcon, FALSE);		// Set small icon


	for (int i = 0; i < m_NI->getAdapterNum(); i++){
		m_NICSet.AddString(m_NI->getAdapterName(i));
	}

	m_lcARPTable.InsertColumn(0, "              IP addr", LVCFMT_CENTER, 128);
	m_lcARPTable.InsertColumn(1, "MAC addr", LVCFMT_CENTER, 128);
	m_lcARPTable.InsertColumn(2, "State", LVCFMT_CENTER, 81);

	m_lcProxyTable.InsertColumn(0, "                    IP addr", LVCFMT_CENTER, 168);
	m_lcProxyTable.InsertColumn(1, "MAC addr", LVCFMT_CENTER, 169);

	return TRUE;  // return TRUE  unless you set the focus to a control
}

// If you add a minimize button to your dialog, you will need the code below
//  to draw the icon.  For MFC applications using the document/view model,
//  this is automatically done for you by the framework.

void CARPDlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // device context for painting

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// Center icon in client rectangle
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// Draw the icon
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

// The system calls this function to obtain the cursor to display while the user drags
//  the minimized window.
HCURSOR CARPDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}

void CARPDlg::OnClickedButtonAddrSet()
{
		UpdateData(TRUE);

		if (!m_bSendReady){
			// if not Setup, make m_bSendReady = TRUE
			m_bSendReady = TRUE;
			SetDlgState(IPC_ADDR_SET);

			m_NI->setAdapterNum(m_NICSet.GetCurSel());
			m_NI->setMacAddress();
			m_SrcMacAddress = m_NI->getMacAddress();
			m_stSrcAdd.Format("%.2X-%.2X-%.2X-%.2X-%.2X-%.2X",
				m_SrcMacAddress[0], m_SrcMacAddress[1], m_SrcMacAddress[2],
				m_SrcMacAddress[3], m_SrcMacAddress[4], m_SrcMacAddress[5]);
		
			CString	m_stDstAdd = "FFFFFFFFFFFF";

			//save des
			m_stDstAdd.MakeLower();	// change LowwerBound
			ConvertHex(m_stDstAdd, m_DstMacAddress);	// CString change to Hex
			m_stDstAdd.Format("%.2X-%.2X-%.2X-%.2X-%.2X-%.2X",
				m_DstMacAddress[0], m_DstMacAddress[1], m_DstMacAddress[2],
				m_DstMacAddress[3], m_DstMacAddress[4], m_DstMacAddress[5]);

			m_Ether->SetSourceAddress(m_SrcMacAddress); //save src and des
			m_Ether->SetDestinAddress(m_DstMacAddress);

			unsigned char srcIP[4];
			m_srcIPAddr.GetAddress(srcIP[0], srcIP[1], srcIP[2], srcIP[3]);
			m_IP->SetSourceAddress(srcIP);
		}
		else {
			// rebutton make not Ready
			m_bSendReady = FALSE;
			m_ARP->initARPTable();
			m_lcARPTable.DeleteAllItems();
			SetDlgState(IPC_ADDR_RESET);
		}
		UpdateData(FALSE);

		m_RecvThread = ::AfxBeginThread(CARPDlg::ReceiveThread, this);
}

BOOL CARPDlg::ConvertHex(CString cs, unsigned char* hex)//change string to hex
{
	int i;
	char* srcStr = cs.GetBuffer(0);

	for (i = 0; i<12; i++){
		// error
		if (srcStr[i] < '0' || (srcStr[i] > '9' && srcStr[i] < 'a') || srcStr[i] > 'f')
			return FALSE;
	}
	for (i = 0; i<12; i = i + 2){
		hex[i / 2] = (((srcStr[i] > '9') ? (srcStr[i] - 87) : (srcStr[i] - '0')) << 4 |
			((srcStr[i + 1] > '9') ? (srcStr[i + 1] - 87) : (srcStr[i + 1] - '0')));
	}
	return TRUE;
}

BOOL CARPDlg::ConvertStringToIP(CString cs, unsigned char* ip)//change string to hex
{
	int j = 0;
	LPCSTR ipString = cs.GetString();

	memset(ip, 0, 4);

	for (int i = 0; i < 4; i++)
	{
		while (ipString[j] != NULL && ipString[j] != '.')
		{
			ip[i] *= 10;
			ip[i] += ipString[j] - '0';
			j++;
		}
		j++;
	}

	return true;
}

void CARPDlg::SetDlgState(int state)
{
	UpdateData(TRUE);

	CButton*	pSetAddrButton = (CButton*)GetDlgItem(IDC_BUTTON_ADDR_SET);

	switch (state)
	{
	case IPC_ADDR_SET:
		pSetAddrButton->SetWindowText("재설정");
		m_NICSet.EnableWindow(false);
		break;
	case IPC_ADDR_RESET:
		pSetAddrButton->SetWindowText("설정");
		m_stSrcAdd = "";
		m_NICSet.EnableWindow(true);
		m_Ether->SetSourceAddress((unsigned char*)"00000000");
		m_Ether->SetDestinAddress((unsigned char*)"00000000");
		break;
	}

	UpdateData(FALSE);
}

UINT CARPDlg::ReceiveThread(LPVOID pParam)
{
	CARPDlg* obj = (CARPDlg*)pParam;

	//if m_bSendReady is on, start Thread
	while (obj->m_bSendReady == TRUE)
	{
		CBaseLayer* bLayer;
		bLayer = obj->m_LayerMgr.GetLayer("Ip");
		unsigned char *ppayload = bLayer->Receive();
		if (ppayload != NULL)
			obj->Receive(ppayload);
	}
	return 0;
}

void CARPDlg::OnClickedButtonSend()
{
	UpdateData(TRUE);

	if (!m_stDesAddr.IsEmpty())
	{
		unsigned char ip[4];
		IPaddr.GetAddress(ip[0], ip[1], ip[2], ip[3]);
		SendARPRequest(ip);
		m_stDesAddr = "";

		(CEdit*)GetDlgItem(IDC_EDIT_DES_ADDR)->SetFocus();

		OnBnClickedButtonRefresh();
	}

	UpdateData(FALSE);
}

void CARPDlg::SendARPRequest(unsigned char* desIP)
{
	if (desIP != NULL)
	mp_UnderLayer->Send(desIP);
}

void CARPDlg::OnIpnFieldchangedEditDesAddr(NMHDR *pNMHDR, LRESULT *pResult)
{
		LPNMIPADDRESS pIPAddr = reinterpret_cast<LPNMIPADDRESS>(pNMHDR);
		// TODO: Add your control notification handler code here
		*pResult = 0;
}


void CARPDlg::OnBnClickedButtonRefresh()
{
	// TODO: Add your control notification handler code here
	char** keyIPTable = NULL;
	int tableSize;

	ARPElement* arpElements = m_ARP->getARPElements(&keyIPTable, &tableSize);

	m_lcARPTable.DeleteAllItems();

	for (int i = 0; i < tableSize; i++)
	{
		CString ipAddr;
		ipAddr.Format("%.2u.%.2u.%.2u.%.2u",
			(unsigned char)keyIPTable[i][0], (unsigned char)keyIPTable[i][1], (unsigned char)keyIPTable[i][2],
			(unsigned char)keyIPTable[i][3]);

		CString macAddr;
		if (arpElements[i].MACAddr != NULL)
			macAddr.Format("%.2X-%.2X-%.2X-%.2X-%.2X-%.2X",
				arpElements[i].MACAddr[0], arpElements[i].MACAddr[1], arpElements[i].MACAddr[2],
				arpElements[i].MACAddr[3], arpElements[i].MACAddr[4], arpElements[i].MACAddr[5]);
		else
			macAddr.Format("??-??-??-??-??-??");

		m_lcARPTable.InsertItem(i, ipAddr);
		m_lcARPTable.SetItem(i, 1, LVIF_TEXT, macAddr, 0, 0, 0, NULL);
		if (arpElements[i].state == ARP_COMPLETE)
			m_lcARPTable.SetItem(i, 2, LVIF_TEXT, "COMPLETE", 0, 0, 0, NULL);
		else
			m_lcARPTable.SetItem(i, 2, LVIF_TEXT, "INCOMPLETE", 0, 0, 0, NULL);
	}
}


void CARPDlg::OnBnClickedProxyAddButton()
{
	// TODO: Add your control notification handler code here
	UpdateData(TRUE);

	unsigned char _hexProxyMacAddr[6];
	unsigned char _proxyIPAddr[4];

	if (m_proxyMacAddr.GetLength() != 12)
	{
		MessageBox("MAC 주소 12자리를 입력해 주세요.");
		return;
	}

	ConvertHex(m_proxyMacAddr, _hexProxyMacAddr);	// CString change to Hex
	m_proxyMacAddr.Format("");

	m_proxyIPAddr.GetAddress(_proxyIPAddr[0], _proxyIPAddr[1], _proxyIPAddr[2], _proxyIPAddr[3]);
	m_proxyIPAddr.ClearAddress();

	m_ARP->insertProxyTable(_proxyIPAddr, _hexProxyMacAddr);
	RefreshProxyTable();

	UpdateData(FALSE);
}


void CARPDlg::RefreshProxyTable()
{
	// TODO: Add your control notification handler code here
	char** keyIPTable = NULL;
	int tableSize;

	ARPElement* proxyElements = m_ARP->getProxyElements(&keyIPTable, &tableSize);

	m_lcProxyTable.DeleteAllItems();

	for (int i = 0; i < tableSize; i++)
	{
		CString ipAddr;
		ipAddr.Format("%.2u.%.2u.%.2u.%.2u",
			(unsigned char)keyIPTable[i][0], (unsigned char)keyIPTable[i][1], (unsigned char)keyIPTable[i][2],
			(unsigned char)keyIPTable[i][3]);

		CString macAddr;
		if (proxyElements[i].MACAddr != NULL)
			macAddr.Format("%.2X-%.2X-%.2X-%.2X-%.2X-%.2X",
			proxyElements[i].MACAddr[0], proxyElements[i].MACAddr[1], proxyElements[i].MACAddr[2],
			proxyElements[i].MACAddr[3], proxyElements[i].MACAddr[4], proxyElements[i].MACAddr[5]);
		else
			macAddr.Format("??-??-??-??-??-??");

		m_lcProxyTable.InsertItem(i, ipAddr);
		m_lcProxyTable.SetItem(i, 1, LVIF_TEXT, macAddr, 0, 0, 0, NULL);
		if (proxyElements[i].state == ARP_COMPLETE)
			m_lcProxyTable.SetItem(i, 2, LVIF_TEXT, "COMPLETE", 0, 0, 0, NULL);
		else
			m_lcProxyTable.SetItem(i, 2, LVIF_TEXT, "INCOMPLETE", 0, 0, 0, NULL);
	}
}

void CARPDlg::OnBnClickedProxyDeleteButton()
{
	// TODO: Add your control notification handler code here
	UpdateData(TRUE);

	POSITION pos = m_lcProxyTable.GetFirstSelectedItemPosition();
	int nItem = m_lcProxyTable.GetNextSelectedItem(pos);
	CString strIP = m_lcProxyTable.GetItemText(nItem, 0);

	unsigned char* deletedIP = new unsigned char[4];

	ConvertStringToIP(strIP, deletedIP);
	m_ARP->deleteProxyTable(deletedIP);

	RefreshProxyTable();

	UpdateData(FALSE);
}


void CARPDlg::OnBnClickedGratuitousSend()
{
	UpdateData(TRUE);

	if (!m_strGratuitousAddr.IsEmpty())
{
		if (m_strGratuitousAddr.GetLength() != 12)
		{
			MessageBox("MAC 주소 12자리를 입력해 주세요.");
			return;
		}

		unsigned char* hexMac = new unsigned char[6];
		ConvertHex(m_strGratuitousAddr, hexMac);
		m_Ether->SetSourceAddress(hexMac);

		unsigned char _destAdd[6];
		memset(_destAdd, 0xff, 6);
		m_Ether->SetDestinAddress(_destAdd);

		m_stSrcAdd.Format("%.2X-%.2X-%.2X-%.2X-%.2X-%.2X",
			hexMac[0], hexMac[1], hexMac[2],
			hexMac[3], hexMac[4], hexMac[5]);

		SendARPRequest(m_IP->GetSourceAddress());
		m_strGratuitousAddr = "";
	}

	UpdateData(FALSE);
}
