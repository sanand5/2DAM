Public Class Entradas
    Private inv As New List(Of Class1)
    Dim prod1 As New Class1("Adulto", 30, "Mayores de 18 años y menores de 65 años")
    Dim prod2 As New Class1("Junior", 20, "Menores de 18 años")
    Dim prod3 As New Class1("Senior", 15, "Mayores de 65 años")
    Dim prod4 As New Class1("Niños", 5, "Menores de 5 años")
    Dim inventario As String = ""

    Private Sub MostrarInv()
        TextBox1.Clear()
        inv.Add(prod1)
        inv.Add(prod2)
        inv.Add(prod3)
        inv.Add(prod4)
        inventario = "Tipo" & vbTab & "Precio" & vbTab & "Definicion" & vbCrLf
        For Each it In inv
            inventario &= it.ToString
        Next
        TextBox1.Text = inventario


    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        MostrarInv()
    End Sub
End Class