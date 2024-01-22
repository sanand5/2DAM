
Public Class Productos
    Public Nombre As String
    Public Precio As Double
    Public Cantidad As Integer
    Public Total As Integer

    Public Sub New(nombre As String, precio As Double, cantidad As Integer)
        Me.Nombre = nombre
        Me.Precio = precio
        Me.Cantidad = cantidad
        Me.Total = cantidad * precio
    End Sub

    Public Overrides Function ToString() As String
        Return $"{Nombre}{vbTab}{Cantidad}{vbTab}{Precio:C}{vbTab}{Total:C}"
    End Function
End Class

