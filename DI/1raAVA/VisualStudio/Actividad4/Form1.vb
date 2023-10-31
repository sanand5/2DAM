Public Class Form1
    Private Sub Button1_mv(sender As Object, e As EventArgs) Handles mv_btn.MouseEnter
        If mv_btn.Top = 12 Then
            mv_btn.Top = 216
        Else
            mv_btn.Top = 12
        End If
    End Sub
End Class
