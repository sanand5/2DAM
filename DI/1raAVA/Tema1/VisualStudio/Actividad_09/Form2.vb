Public Class Form2
    Dim num As Integer
    Private Sub bt_cl_Click(sender As Object, e As EventArgs) Handles bt_cl.Click
        num = 0
        Form1.lbl_cont.Text = "Contador: 0"
    End Sub

    Private Sub bt_add_Click(sender As Object, e As EventArgs) Handles bt_add.Click
        num += 1
        Form1.lbl_cont.Text = "Contador: " + num.ToString
    End Sub
End Class