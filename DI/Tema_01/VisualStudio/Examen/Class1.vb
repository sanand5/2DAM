Public Class Class1
    Public Property type As String
    Public Property price As Double
    Public Property def As String

    Public Sub New(type As String, price As Double, def As String)
        Me.type = type
        Me.price = price
        Me.def = def
    End Sub

    Public Overrides Function ToString() As String
        Return $"{type}{vbTab}{price:C}{vbTab}{def}" & vbCrLf
    End Function

End Class
