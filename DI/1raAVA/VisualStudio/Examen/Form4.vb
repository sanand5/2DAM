Public Class Form4

    Private Sub RadioButtonterra_CheckedChanged(sender As Object, e As EventArgs) Handles RadioButtonterra.CheckedChanged

        playa.Hide()
        terramitica.Show()
        benidorm.Hide()
        valencia.Hide()
    End Sub

    Private Sub RadioButtonbeni_CheckedChanged(sender As Object, e As EventArgs) Handles RadioButtonbeni.CheckedChanged
        playa.Hide()
        terramitica.Hide()
        benidorm.Show()
        valencia.Hide()
    End Sub

    Private Sub RadioButtonVal_CheckedChanged(sender As Object, e As EventArgs) Handles RadioButtonVal.CheckedChanged
        playa.Hide()
        terramitica.Hide()
        benidorm.Hide()
        valencia.Show()
    End Sub

    Private Sub RadioButtonPlaya_CheckedChanged(sender As Object, e As EventArgs) Handles RadioButtonPlaya.CheckedChanged
        playa.Show()
        terramitica.Hide()
        benidorm.Hide()
        valencia.Hide()
    End Sub
End Class