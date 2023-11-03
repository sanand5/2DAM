Imports System.Windows.Forms.Design.AxImporter

Public Class Form1
    Dim num1 As Double
    Dim num2 As Double
    Dim operador As String
    Dim clear As Boolean
    Private Sub numeros(sender As Object, e As EventArgs) Handles bt_01.Click, btn_02.Click, btn_03.Click, btn_04.Click, btn_05.Click, btn_06.Click, btn_07.Click, btn_08.Click, btn_09.Click, btn_0.Click
        If clear Then
            tb_calc.Text = ""
            clear = False
        End If

        Dim numBtn As Button = DirectCast(sender, Button)
        tb_calc.Text += numBtn.Text

    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        tb_calc.Text = ""
    End Sub

    Private Sub guardar(sender As Object, e As EventArgs) Handles btn_mas.Click, btn_menos.Click, btn_por.Click, btn_div.Click
        num1 = Double.Parse(tb_calc.Text)
        tb_calc.Text = ""
        Dim boton As Button = DirectCast(sender, Button)
        operador = boton.Text
    End Sub

    Private Sub btn_igual_Click(sender As Object, e As EventArgs) Handles btn_igual.Click
        Dim res As Double
        num2 = Double.Parse(tb_calc.Text)
        clear = True

        Select Case operador
            Case "+"
                res = num1 + num2
            Case "-"
                res = num1 - num2
            Case "*"
                res = num1 * num2
            Case "/"
                res = num1 / num2
        End Select
        tb_calc.Text = res.ToString
    End Sub

    Private Sub btn_coma_Click(sender As Object, e As EventArgs) Handles btn_coma.Click
        If Not tb_calc.Text.Contains(",") Then
            tb_calc.Text += ","
        End If
    End Sub
End Class

