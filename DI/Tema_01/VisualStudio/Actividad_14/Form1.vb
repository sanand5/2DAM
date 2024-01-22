Public Class Form1

    Dim inventario As New List(Of Productos)

    Private Sub MostrarInventario()
        inventario.Clear()
        inventario.Add(New Productos("Camisa", 29.99, 5))
        inventario.Add(New Productos("Pantalón", 39.99, 3))
        inventario.Add(New Productos("Zapatos", 49.99, 2))
        inventario.Add(New Productos("Gorro", 19.99, 4))
        inventario.Add(New Productos("Bufanda", 14.99, 6))
        inventario.Add(New Productos("Jeans", 34.99, 2))
        inventario.Add(New Productos("Camiseta", 19.99, 8))

        Dim tabla As String
        tabla = "Nombre" & vbTab & "Cantidad" & vbTab & "Precio" & vbTab & "Total" & vbCrLf
        For i As Integer = 0 To inventario.Count - 1
            Dim producto As Productos = DirectCast(inventario(i), Productos)
            tabla += producto.ToString() & vbCrLf
        Next
        inv_txtb.Text = tabla
    End Sub

    Private Sub btnMostrar_Click_1(sender As Object, e As EventArgs) Handles btnMostrar.Click
        MostrarInventario()
    End Sub
End Class
