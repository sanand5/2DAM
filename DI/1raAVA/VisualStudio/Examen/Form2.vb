Public Class Form2
    Private Sub IToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles IToolStripMenuItem.Click

    End Sub

    Private Sub CalcularEntradasToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles CalcularEntradasToolStripMenuItem.Click

    End Sub

    Private Sub HistoriaToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles HistoriaToolStripMenuItem.Click
        MsgBox("¿Sabías que el parque Terra Mñica fue construido siguiendo las directrices de un grupo de historiadores expertos en cultura clásica? Los jerogllficose por ejemplo, que hayamos en los muros de ciertas edificaciones del área de Egipto, como las Puertas de Karnak, fueron realizados a mano por egiptólogos. Y es que este complejo es mucho más que diversióm«s un paseo por la historia puesto que su entorno incita a viajar en &$empo sin salir del entorno.", , "Historia de Terra Mítica")
    End Sub

    Private Sub ParqueToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles ParqueToolStripMenuItem.Click
        Parque.Show()
    End Sub

    Private Sub UbicacionesToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles UbicacionesToolStripMenuItem.Click
        Form4.Show()
    End Sub

    Private Sub AcercaDeToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles AcercaDeToolStripMenuItem.Click
        Form5.Show()
    End Sub

    Private Sub PictureBox1_Click(sender As Object, e As EventArgs) Handles PictureBox1.Click
        Form1.Close()
    End Sub

    Private Sub PreciosToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles PreciosToolStripMenuItem.Click
        Entradas.Show()
    End Sub
End Class