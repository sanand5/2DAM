<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Entradas
    Inherits System.Windows.Forms.Form

    'Form reemplaza a Dispose para limpiar la lista de componentes.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Requerido por el Diseñador de Windows Forms
    Private components As System.ComponentModel.IContainer

    'NOTA: el Diseñador de Windows Forms necesita el siguiente procedimiento
    'Se puede modificar usando el Diseñador de Windows Forms.  
    'No lo modifique con el editor de código.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Dim resources As ComponentModel.ComponentResourceManager = New ComponentModel.ComponentResourceManager(GetType(Entradas))
        TextBox1 = New TextBox()
        Button1 = New Button()
        SuspendLayout()
        ' 
        ' TextBox1
        ' 
        TextBox1.Font = New Font("Rockwell Condensed", 20F, FontStyle.Regular, GraphicsUnit.Point)
        TextBox1.Location = New Point(12, 12)
        TextBox1.Multiline = True
        TextBox1.Name = "TextBox1"
        TextBox1.Size = New Size(553, 249)
        TextBox1.TabIndex = 0
        ' 
        ' Button1
        ' 
        Button1.Font = New Font("Rockwell Condensed", 20F, FontStyle.Regular, GraphicsUnit.Point)
        Button1.Location = New Point(193, 277)
        Button1.Name = "Button1"
        Button1.Size = New Size(190, 57)
        Button1.TabIndex = 1
        Button1.Text = "Ver Precios"
        Button1.UseVisualStyleBackColor = True
        ' 
        ' Entradas
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(577, 346)
        Controls.Add(Button1)
        Controls.Add(TextBox1)
        Icon = CType(resources.GetObject("$this.Icon"), Icon)
        Name = "Entradas"
        Text = "Entradas"
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents TextBox1 As TextBox
    Friend WithEvents Button1 As Button
End Class
