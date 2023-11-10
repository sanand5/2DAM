Imports System.Windows.Forms.VisualStyles.VisualStyleElement.TrayNotify

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
        Me.KeyPreview = True
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

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles btn_bgcolor.Click
        Dim colorDialog As New ColorDialog()

        If colorDialog.ShowDialog() = DialogResult.OK Then
            Me.BackColor = colorDialog.Color
        End If
    End Sub

    Private Sub btn_fgcolor_Click(sender As Object, e As EventArgs) Handles btn_fgcolor.Click
        Dim colorDialog As New ColorDialog()
        If colorDialog.ShowDialog() = DialogResult.OK Then
            lb_lower.ForeColor = colorDialog.Color
            lb_num.ForeColor = colorDialog.Color
            lb_upper.ForeColor = colorDialog.Color
            rb_all.ForeColor = colorDialog.Color
            rb_letter.ForeColor = colorDialog.Color
            rb_num.ForeColor = colorDialog.Color
        End If
    End Sub

    Private Sub BackgroundColorToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles mn_bgcolor.Click
        Dim colorDialog As New ColorDialog()

        If colorDialog.ShowDialog() = DialogResult.OK Then
            Me.BackColor = colorDialog.Color
        End If

    End Sub

    Private Sub FontColorToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles mn_fcolor.Click
        Dim colorDialog As New ColorDialog()
        If colorDialog.ShowDialog() = DialogResult.OK Then
            lb_lower.ForeColor = colorDialog.Color
            lb_num.ForeColor = colorDialog.Color
            lb_upper.ForeColor = colorDialog.Color
            rb_all.ForeColor = colorDialog.Color
            rb_letter.ForeColor = colorDialog.Color
            rb_num.ForeColor = colorDialog.Color
        End If
    End Sub
End Class