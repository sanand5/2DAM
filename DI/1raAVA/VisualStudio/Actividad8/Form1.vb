Public Class Form1
    Private Sub bt_01_Click(sender As Object, e As EventArgs) Handles bt_01.Click
        tb_calc.Text += bt_01.Text
    End Sub

    Private Sub bt_02_Click(sender As Object, e As EventArgs) Handles btn_02.Click
        tb_calc.Text += btn_02.Text
    End Sub

    Private Sub bt_03_Click(sender As Object, e As EventArgs) Handles btn_03.Click
        tb_calc.Text += btn_03.Text
    End Sub

    Private Sub bt_04_Click(sender As Object, e As EventArgs) Handles btn_04.Click
        tb_calc.Text += btn_04.Text
    End Sub

    Private Sub bt_05_Click(sender As Object, e As EventArgs) Handles btn_05.Click
        tb_calc.Text += btn_05.Text
    End Sub

    Private Sub bt_06_Click(sender As Object, e As EventArgs) Handles btn_06.Click
        tb_calc.Text += btn_06.Text
    End Sub

    Private Sub bt_07_Click(sender As Object, e As EventArgs) Handles btn_07.Click
        tb_calc.Text += btn_07.Text
    End Sub
    Private Sub bt_08_Click(sender As Object, e As EventArgs) Handles btn_08.Click
        tb_calc.Text += btn_08.Text
    End Sub
    Private Sub bt_09_Click(sender As Object, e As EventArgs) Handles btn_09.Click
        tb_calc.Text += btn_09.Text
    End Sub
    Private Sub bt_0_Click(sender As Object, e As EventArgs) Handles btn_00.Click
        tb_calc.Text += btn_00.Text
    End Sub

    Private Sub btn_Clear_Click(sender As Object, e As EventArgs) Handles btn_Clear.Click
        tb_calc.Clear()
    End Sub

    Private Sub btn_Eur_Dol_Click(sender As Object, e As EventArgs) Handles btn_Eur_Dol.Click
        Dim num As Double
        num = CDbl(tb_calc.Text)
        tb_calc.Text = (num * 1.0615038).ToString("0.00")
    End Sub

    Private Sub btn_Dol_Eur_Click(sender As Object, e As EventArgs) Handles btn_Dol_Eur.Click
        Dim num As Double
        num = tb_calc.Text
        tb_calc.Text = FormatCurrency((num * 0.94205973).ToString("0.00"))
    End Sub
End Class
