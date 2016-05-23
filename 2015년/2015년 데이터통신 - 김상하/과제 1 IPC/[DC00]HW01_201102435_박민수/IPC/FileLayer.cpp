// FileLayer.cpp: implementation of the CFileLayer class.
//
//////////////////////////////////////////////////////////////////////

#include "stdafx.h"
#include "ipc.h"
#include "FileLayer.h"

#ifdef _DEBUG
#undef THIS_FILE
static char THIS_FILE[]=__FILE__;
#define new DEBUG_NEW
#endif

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

CFileLayer::CFileLayer( char* pName )
	: CBaseLayer( pName )
{
}

CFileLayer::~CFileLayer()
{
	TRY
	{
		//////////////////////// fill the blank ///////////////////////////////
		// 프로그램이 종료될때 IpcBuff.txt 파일을 지워준다.
		CFile::Remove("IpcBuff.txt");
		///////////////////////////////////////////////////////////////////////
	}
	CATCH( CFileException, e )
	{
#ifdef _DEBUG
		afxDump << "File cannot be removed\n";
#endif
	}
	END_CATCH
}

BOOL CFileLayer::Send(unsigned char *ppayload, int nlength)
{
	TRY
	{
		CFile m_FileDes( "IpcBuff.txt", 
			CFile::modeCreate | CFile::modeWrite ) ;
		//////////////////////// fill the blank ///////////////////////////////
		// 위에서 받아온 데이터를 공유파일에 기록한다.
		m_FileDes.Write(ppayload, nlength);
		m_FileDes.Close();
		///////////////////////////////////////////////////////////////////////
	}
	CATCH( CFileException, e )
	{
#ifdef _DEBUG
		afxDump << "File could not be opened " << e->m_cause << "\n";
#endif
		return FALSE ;
	}
	END_CATCH

		return TRUE ;
}

BOOL CFileLayer::Receive( )
{
	unsigned char m_File_data[ETHER_MAX_SIZE];
	memset(m_File_data, 0, ETHER_MAX_SIZE);

	TRY
	{
		CFile m_FileDes( "IpcBuff.txt", CFile::modeRead ) ;

		//////////////////////// fill the blank ///////////////////////////////
		// 송신 프로세스가 파일 전송 완료 메시지를 모든 프로세스에 전달하면 
		// 공유파일로 부터 저장된 데이터를 읽어들인다. 읽어들인 데이터를 
		// 상위계층으로 넘겨준다.
		int nlength = ETHER_HEADER_SIZE + ETHER_MAX_DATA_SIZE;
		unsigned char* ppayload = new unsigned char[nlength+1];

		m_FileDes.Read(ppayload, nlength);
		ppayload[nlength] = '\0';

		if(!mp_aUpperLayer[0]->Receive(ppayload))
		{
			m_FileDes.Close();
			return FALSE;
		}
		///////////////////////////////////////////////////////////////////////

		m_FileDes.Close( ) ;
	}
	CATCH( CFileException, e )
	{
#ifdef _DEBUG
		afxDump << "File could not be opened " << e->m_cause << "\n";
#endif
		return FALSE ;
	}
	END_CATCH

		return TRUE ;
}
