Imports System.Windows.Forms.VisualStyles.VisualStyleElement
Imports System.Windows.Forms.VisualStyles.VisualStyleElement.Button

Public Class Form1


    Private Sub Button3_Click(sender As Object, e As EventArgs) Handles cls_btn.Click
        Close()
    End Sub

    Private Sub res_bt_Click(sender As Object, e As EventArgs) Handles res_bt.Click
        Dim res As Double
        Try
            If sm_rb.Checked Then
                res = num_tb.Text + num2_tb.Text
            ElseIf rs_rb.Checked Then
                res = num_tb.Text - num2_tb.Text
            ElseIf ml_rb.Checked Then
                res = num_tb.Text * num2_tb.Text
            ElseIf dv_rb.Checked Then
                res = num_tb.Text / num2_tb.Text
            End If
            res_lbl.Text = res
        Catch
            MsgBox("Algún número contiene letras", MsgBoxStyle.Exclamation, "Error Inesperado")
        End Try

    End Sub

    Private Sub sm_rb_CheckedChanged(sender As Object, e As EventArgs) Handles sm_rb.CheckedChanged
        mas_lbl.Text = "+"
    End Sub

    Private Sub rs_rb_CheckedChanged(sender As Object, e As EventArgs) Handles rs_rb.CheckedChanged
        mas_lbl.Text = "-"
    End Sub

    Private Sub ml_rb_CheckedChanged(sender As Object, e As EventArgs) Handles ml_rb.CheckedChanged
        mas_lbl.Text = "x"
    End Sub

    Private Sub dv_rb_CheckedChanged(sender As Object, e As EventArgs) Handles dv_rb.CheckedChanged
        mas_lbl.Text = "/"
    End Sub
End Class
