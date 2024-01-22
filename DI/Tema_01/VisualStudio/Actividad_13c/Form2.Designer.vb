<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form2
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
        Dim resources As ComponentModel.ComponentResourceManager = New ComponentModel.ComponentResourceManager(GetType(Form2))
        TextBox1 = New TextBox()
        btn_bgcolor = New Button()
        SuspendLayout()
        ' 
        ' TextBox1
        ' 
        TextBox1.Font = New Font("Yu Gothic UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        TextBox1.Location = New Point(12, 12)
        TextBox1.Multiline = True
        TextBox1.Name = "TextBox1"
        TextBox1.ReadOnly = True
        TextBox1.ScrollBars = ScrollBars.Vertical
        TextBox1.ShortcutsEnabled = False
        TextBox1.Size = New Size(311, 161)
        TextBox1.TabIndex = 0
        TextBox1.TabStop = False
        TextBox1.Text = resources.GetString("TextBox1.Text")
        ' 
        ' btn_bgcolor
        ' 
        btn_bgcolor.AccessibleName = "btn_bgcolor"
        btn_bgcolor.Font = New Font("Segoe UI", 11F, FontStyle.Regular, GraphicsUnit.Point)
        btn_bgcolor.Location = New Point(105, 181)
        btn_bgcolor.Name = "btn_bgcolor"
        btn_bgcolor.Size = New Size(125, 28)
        btn_bgcolor.TabIndex = 7
        btn_bgcolor.Text = "Aceptar"
        btn_bgcolor.UseVisualStyleBackColor = True
        ' 
        ' Form2
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(335, 216)
        Controls.Add(btn_bgcolor)
        Controls.Add(TextBox1)
        Name = "Form2"
        StartPosition = FormStartPosition.CenterScreen
        Text = "Actividad 13 Help"
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents TextBox1 As TextBox
    Friend WithEvents btn_bgcolor As Button
End Class
