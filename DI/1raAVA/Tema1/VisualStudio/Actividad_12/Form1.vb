Public Class ASCII

    Private Sub cls_btn_Click(sender As Object, e As EventArgs) Handles cls_btn.Click
        cb_letters.Text = ""
        cb_num.Text = ""
        cb_other.Text = ""
        cb_text.Text = ""
    End Sub

    Private Sub sub_btn_Click(sender As Object, e As EventArgs) Handles sub_btn.Click
        Dim texto As String = cb_text.Text
        Dim letters As String = ""
        Dim numbers As String = ""
        Dim other As String = ""

        For i As Integer = 1 To texto.Length
            Dim c As Char = texto(i - 1)
            Try
                Dim asciiValue As Integer = Asc(c)

                If (asciiValue >= 65 And asciiValue <= 90) Or (asciiValue >= 97 And asciiValue <= 122) Then
                    letters += c
                ElseIf asciiValue >= 48 And asciiValue <= 57 Then
                    numbers += c
                Else
                    other += c
                End If

            Catch ex As Exception
                other += c
            End Try
        Next

        ' Mostrar las cadenas resultantes en los cuadros de texto correspondientes
        cb_letters.Text = letters
        cb_num.Text = numbers
        cb_other.Text = other
    End Sub

    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load

    End Sub
End Class
