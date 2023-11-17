Public Class Form1
    Private Sub Form1_KeyPress(sender As Object, e As KeyPressEventArgs) Handles MyBase.KeyPress
        Dim teclaPresionada As Char = e.KeyChar
        lbl_char.Text = teclaPresionada
        lbl_num.Text = "Number: " + Asc(teclaPresionada).ToString
    End Sub
End Class
