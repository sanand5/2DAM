Imports System.Windows.Forms.VisualStyles.VisualStyleElement

Public Class Form1



    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click, calc_bt.Click
        Close()
    End Sub


    Private Sub Button1_(sender As Object, e As EventArgs) Handles calc_bt.MouseEnter
        res_lbl.Text = "tocado"
    End Sub

    Private Sub calc_bt_DragLeave(sender As Object, e As EventArgs) Handles calc_bt.DragLeave
        res_lbl.Text = "no tocado"
    End Sub
End Class
