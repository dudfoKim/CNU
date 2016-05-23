
// stdafx.h : include file for standard system include files,
// or project specific include files that are used frequently,
// but are changed infrequently

#pragma once

#ifndef VC_EXTRALEAN
#define VC_EXTRALEAN            // Exclude rarely-used stuff from Windows headers
#endif

#include "targetver.h"

#define _ATL_CSTRING_EXPLICIT_CONSTRUCTORS      // some CString constructors will be explicit

// turns off MFC's hiding of some common and often safely ignored warning messages
#define _AFX_ALL_WARNINGS

#include <afxwin.h>         // MFC core and standard components
#include <afxext.h>         // MFC extensions

#define MAX_LAYER_NUMBER		0xff

#define ETHER_MAX_SIZE			1514//+300
#define ETHER_HEADER_SIZE		14
#define ETHER_MAX_DATA_SIZE		( ETHER_MAX_SIZE - ETHER_HEADER_SIZE )

#define IP_HEADER_SIZE			10
#define IP_DATA_SIZE			1490

#define TCP_HEADER_SIZE			6
#define TCP_DATA_SIZE			1484

#define CHAT_REC				0x01//used APP_DATA_MSG (REC)
#define CHAT_NOTREC				0x02//used APP_DATA_MSG (NOTREC)

#define ARP_TYPE				0x0806
#define	CHAT_TYPE				0x2080
#define	FILE_TYPE				0x2090

//#define DATA_HEAD				0x00
//#define DATA_MAIN				0x01
//#define DATA_LAST				0x02

//4 byte
#define APP_HEADER_SIZE			( sizeof(unsigned char)  +		    		\
								  sizeof(unsigned short) +					\
								  sizeof(unsigned char)	)
#define APP_DATA_SIZE			( ETHER_MAX_DATA_SIZE - ( APP_HEADER_SIZE +		\
												          TCP_HEADER_SIZE +		\
												          IP_HEADER_SIZE ) )

typedef struct _ARPElement
{
	unsigned char* MACAddr;
	char state;
	DWORD tick;
}ARPElement;

#define ARP_COMPLETE_TIMEOUT 1200000
#define ARP_INCOMPLETE_TIMEOUT 180000

enum { ARP_INCOMPLETE, ARP_COMPLETE };
enum { ARP_REQUEST = 0x0100, ARP_REPLY = 0x0200 };


#ifndef _AFX_NO_OLE_SUPPORT
#include <afxdtctl.h>           // MFC support for Internet Explorer 4 Common Controls
#endif
#ifndef _AFX_NO_AFXCMN_SUPPORT
#include <afxcmn.h>             // MFC support for Windows Common Controls
#endif // _AFX_NO_AFXCMN_SUPPORT

#include <afxcontrolbars.h>     // MFC support for ribbons and control bars









#ifdef _UNICODE
#if defined _M_IX86
#pragma comment(linker,"/manifestdependency:\"type='win32' name='Microsoft.Windows.Common-Controls' version='6.0.0.0' processorArchitecture='x86' publicKeyToken='6595b64144ccf1df' language='*'\"")
#elif defined _M_X64
#pragma comment(linker,"/manifestdependency:\"type='win32' name='Microsoft.Windows.Common-Controls' version='6.0.0.0' processorArchitecture='amd64' publicKeyToken='6595b64144ccf1df' language='*'\"")
#else
#pragma comment(linker,"/manifestdependency:\"type='win32' name='Microsoft.Windows.Common-Controls' version='6.0.0.0' processorArchitecture='*' publicKeyToken='6595b64144ccf1df' language='*'\"")
#endif
#endif


