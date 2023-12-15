Imports WMPLib

Public Class VentanaSwitches
    Private Sub PictureBox1_Click(sender As Object, e As EventArgs) Handles PictureBox1.Click
        My.Computer.Audio.Play(My.Resources._1, AudioPlayMode.WaitToComplete)
    End Sub
End Class
