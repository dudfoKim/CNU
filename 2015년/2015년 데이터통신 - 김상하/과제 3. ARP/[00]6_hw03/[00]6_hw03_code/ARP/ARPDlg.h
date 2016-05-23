
// ARPDlg.h : header file
//

#pragma once
#include "LayerManager.h"	// Added by ClassView
#include "EthernetLayer.h"	// Added by ClassView
#include "IPLayer.h"	// Added by ClassView
#include "ARPLayer.h"
#include "NILayer.h"
#include "afxwin.h"
#include "afxcmn.h"


// CARPDlg dialog
class CARPDlg : public CDialogEx, public CBaseLayer
{
// Construction
public:
	CARPDlg(CWnd* pParent = NULL);	// standard constructor

// Dialog Data
	enum { IDD = IDD_ARP_DIALOG };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV support

	BOOL ConvertHex(CString cs, unsigned char* hex);			//change string to hex for mac address
	BOOL ConvertStringToIP(CString cs, unsigned char* ip);		//change string to ip address
	void SetDlgState(int state);
	void SendARPRequest(unsigned char* desIP);
	static UINT	ReceiveThread(LPVOID pParam);
	void RefreshProxyTable();


// Implementation
protected:
	HICON m_hIcon;

	// Generated message map functions
	virtual BOOL OnInitDialog();
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()

private:
	unsigned char*	m_SrcMacAddress;	// 시작지의 Mac주소를 가리키는 포인터
	// 실제배열은 NI계층에 존재
	unsigned char	m_DstMacAddress[6];	// 목적지의 Mac주소
	BOOL			m_bSendReady;
	enum {
		IPC_ADDR_SET,
		IPC_ADDR_RESET
	};

	// Object App
	CLayerManager	m_LayerMgr;
	CEthernetLayer* m_Ether;
	ARPLayer* m_ARP;
	CIPLayer* m_IP;
	CNILayer* m_NI;
	CWinThread*	m_RecvThread;

public:
	CString m_stSrcAdd;
	CComboBox m_NICSet;
	afx_msg void OnClickedButtonAddrSet();
	afx_msg void OnClickedButtonSend();
	CString m_stDesAddr;

	afx_msg void OnIpnFieldchangedEditDesAddr(NMHDR *pNMHDR, LRESULT *pResult);
	CIPAddressCtrl IPaddr;
	CIPAddressCtrl m_srcIPAddr;
	CListCtrl m_lcARPTable;
	afx_msg void OnBnClickedButtonRefresh();
	CListCtrl m_lcProxyTable;
	CIPAddressCtrl m_proxyIPAddr;
	CString m_proxyMacAddr;
	afx_msg void OnBnClickedProxyAddButton();
	afx_msg void OnBnClickedProxyDeleteButton();
	CString m_strGratuitousAddr;
	afx_msg void OnBnClickedGratuitousSend();
};
