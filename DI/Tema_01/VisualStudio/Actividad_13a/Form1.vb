Public Class Form1
    Private Sub Form1_KeyPress(sender As Object, e As KeyPressEventArgs) Handles MyBase.KeyPress, rb_letter.KeyPress, rb_num.KeyPress, rb_all.KeyPress
        Dim teclaPresionada As Char = e.KeyChar
        If rb_all.Checked Then
            If Char.IsLetter(teclaPresionada) Then
                letras(teclaPresionada)
            Else
                numeros(teclaPresionada)
            End If
        End If

        If rb_letter.Checked And Char.IsLetter(teclaPresionada) Then
            letras(teclaPresionada)
        End If

        If rb_num.Checked And Char.IsNumber(teclaPresionada) Then
            numeros(teclaPresionada)
        End If

    End Sub
    Function letras(chr As Char) As Nullable
        lb_upper.Visible = True
        lb_lower.Visible = True
        lb_num.Visible = False
        lb_upper.Text = "Upper Case: " + chr.ToString.ToUpper
        lb_lower.Text = "Lower Case: " + chr.ToString.ToLower
    End Function
    Function numeros(chr As Char) As Nullable
        lb_upper.Visible = False
        lb_lower.Visible = False
        lb_num.Visible = True
        lb_num.Text = "Number: " + chr.ToString.ToUpper
    End Function

    Private Sub btn_close_Click(sender As Object, e As EventArgs) Handles btn_close.Click
        Close()
    End Sub
End Class
