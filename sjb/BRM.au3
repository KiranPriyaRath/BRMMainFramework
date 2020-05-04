    #include <File.au3>
	#include <MsgBoxConstants.au3>


    Run("C:\\Program Files (x86)\\PuTTY\\putty.exe")

    ; Wait 10 seconds for the putty window to appear.
    Local $hWnd = WinWait("[CLASS:PuTTYConfigBox]", "", 10)
              ;ControlClick("PuTTY Configuration", "", "[CLASS:PuTTYConfigBox; TEXT:Load, save or delete a stored session;INSTANCE:14]")

              $file1 = FileOpen("Y:\KiranRath\OpenUI-SJBMainBRM\BRM_Outputs\Hostname.txt",0)
              While 1
                             $line1 = FileReadLine($file1)
                             if @error = -1 Then ExitLoop
                             Send($line1)
                             ;MsgBox($MB_SYSTEMMODAL, "", $line1)
                             WEnd
              FileClose($file1)
              ;Send("NewVoE7-crm01")
              Send("+{TAB}")
              Send("{DOWN}")
              Send("{TAB}")
              Send("{DOWN}")
              Send("{DOWN}")
              Send("{TAB}")
              Sleep(5)
              $file = FileOpen("Y:\KiranRath\OpenUI-SJBMainBRM\BRM_Outputs\logfile.txt",0)
              While 1
                             $line = FileReadLine($file)
                             if @error = -1 Then ExitLoop
                             Send($line)
                             ;MsgBox($MB_SYSTEMMODAL, "", $line)
                             WEnd
              FileClose($file)
              ;Send("abc")
              Send("{TAB}")
              Send("{TAB}")
              Send("{UP}")
              Send("{TAB}")

              Local $hWnd = WinWait("[CLASS:Button]", "", 10)
;ControlClick ( "title", "text", controlID [, button = "left" [, clicks = 1 [, x [, y]]]] )
ControlClick ( "PuTTY Configuration", "", "[INSTANCE:1]" )
Send("{TAB}")
Local $hWnd = WinWait("[ID:6]", "", 10)
ControlClick ( "", "", "[CLASSNAMENN:Button1,INSTANCE:1]" )
;Local $hWnd = WinWait("[CLASS:Button]", "", 10)
;ControlClick ( "title", "text", controlID [, button = "left" [, clicks = 1 [, x [, y]]]] )
;ControlClick ( "", "", "[INSTANCE:1]" )

;Sleep(5000)
;ControlClick ( "","", "[INSTANCE:1]" )

;Sleep(2000)
;ControlClick ( "","", "[INSTANCE:1]" )


              ;Send("{TAB}")
              ;Send("{TAB}")
              ;Send("{TAB}")
              ;Send("Ankesh")ControlSend ( "title", "text", controlID, "string" [, flag = 0] )

   ; Set the edit control in Notepad with some text. The handle returned by WinWait is used for the "title" parameter of ControlSetText.
  ; ControlSend("PuTTY Configuration", "", "[CLASS:PuTTYConfigBox; INSTANCE:14]", "Ankesh Bansal")
;ControlSend("PuTTY Configuration", "", "[CLASS:PuTTYConfigBox; TEXT:Load, save or delete a stored session; INSTANCE:14]")
;ControlSend("PuTTY Configuration", "", "[CLASS:PuTTYConfigBox]", "HelloWorld")
;ControlSend("PuTTY Configuration", "","INSTANCE:14", "Ankesh", flag = "0")

    ; Retrieve the text of the edit control in Notepad. The handle returned by WinWait is used for the "title" parameter of ControlGetText.
   ; Local $sText = ControlGetText($hWnd, "", "Button14")

    ; Close the Notepad window using the handle returned by WinWait.