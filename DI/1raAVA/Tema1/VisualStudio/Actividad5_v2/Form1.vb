Imports System.Windows.Forms.VisualStyles.VisualStyleElement

Public Class Form1


    Private Sub Button3_Click(sender As Object, e As EventArgs) Handles cls_btn.Click
        Close()
    End Sub

    Private Sub res_bt_Click(sender As Object, e As EventArgs) Handles res_bt.Click
        Dim num As Double
        Dim num2 As Double
        Double.TryParse(num_tb.Text, num)
        Double.TryParse(num2_tb.Text, num2)
        res_lbl.Text = num + num2
    End Sub
End Class
