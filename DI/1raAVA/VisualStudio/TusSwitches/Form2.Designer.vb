<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Descripcion
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
        Dim resources As ComponentModel.ComponentResourceManager = New ComponentModel.ComponentResourceManager(GetType(Descripcion))
        Label2 = New Label()
        Label3 = New Label()
        Label4 = New Label()
        fuerza = New Label()
        tipo = New Label()
        vida = New Label()
        personalizacion = New Label()
        SuspendLayout()
        ' 
        ' Label2
        ' 
        Label2.AutoSize = True
        Label2.BackColor = Color.Transparent
        Label2.Font = New Font("Calibri", 12F, FontStyle.Bold, GraphicsUnit.Point)
        Label2.ForeColor = Color.White
        Label2.Location = New Point(12, 108)
        Label2.Name = "Label2"
        Label2.Size = New Size(157, 19)
        Label2.TabIndex = 1
        Label2.Text = "Fuerza de actuación : "
        ' 
        ' Label3
        ' 
        Label3.AutoSize = True
        Label3.BackColor = Color.Transparent
        Label3.Font = New Font("Calibri", 12F, FontStyle.Bold, GraphicsUnit.Point)
        Label3.ForeColor = Color.White
        Label3.Location = New Point(14, 184)
        Label3.Name = "Label3"
        Label3.Size = New Size(73, 19)
        Label3.TabIndex = 2
        Label3.Text = "Vida útil :"
        ' 
        ' Label4
        ' 
        Label4.AutoSize = True
        Label4.BackColor = Color.Transparent
        Label4.Font = New Font("Calibri", 12F, FontStyle.Bold, GraphicsUnit.Point)
        Label4.ForeColor = Color.White
        Label4.Location = New Point(12, 260)
        Label4.Name = "Label4"
        Label4.Size = New Size(123, 19)
        Label4.TabIndex = 3
        Label4.Text = "Personalización :"
        ' 
        ' fuerza
        ' 
        fuerza.AutoSize = True
        fuerza.BackColor = Color.Transparent
        fuerza.Font = New Font("Calibri", 12F, FontStyle.Regular, GraphicsUnit.Point)
        fuerza.ForeColor = Color.White
        fuerza.Location = New Point(175, 108)
        fuerza.Name = "fuerza"
        fuerza.Size = New Size(51, 19)
        fuerza.TabIndex = 4
        fuerza.Text = "Fuerza"
        ' 
        ' tipo
        ' 
        tipo.AutoSize = True
        tipo.BackColor = Color.Transparent
        tipo.Font = New Font("Calibri", 24F, FontStyle.Bold, GraphicsUnit.Point)
        tipo.ForeColor = Color.White
        tipo.Location = New Point(12, 50)
        tipo.Name = "tipo"
        tipo.Size = New Size(75, 39)
        tipo.TabIndex = 5
        tipo.Text = "Tipo"
        ' 
        ' vida
        ' 
        vida.AutoSize = True
        vida.BackColor = Color.Transparent
        vida.Font = New Font("Calibri", 12F, FontStyle.Regular, GraphicsUnit.Point)
        vida.ForeColor = Color.White
        vida.Location = New Point(93, 184)
        vida.Name = "vida"
        vida.Size = New Size(38, 19)
        vida.TabIndex = 6
        vida.Text = "Vida"
        ' 
        ' personalizacion
        ' 
        personalizacion.AutoSize = True
        personalizacion.BackColor = Color.Transparent
        personalizacion.Font = New Font("Calibri", 12F, FontStyle.Regular, GraphicsUnit.Point)
        personalizacion.ForeColor = Color.White
        personalizacion.Location = New Point(141, 260)
        personalizacion.Name = "personalizacion"
        personalizacion.Size = New Size(110, 19)
        personalizacion.TabIndex = 7
        personalizacion.Text = "Personalizacion"
        ' 
        ' Descripcion
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        BackColor = SystemColors.ControlDarkDark
        BackgroundImage = My.Resources.Resources.Background
        ClientSize = New Size(400, 331)
        Controls.Add(personalizacion)
        Controls.Add(vida)
        Controls.Add(tipo)
        Controls.Add(fuerza)
        Controls.Add(Label4)
        Controls.Add(Label3)
        Controls.Add(Label2)
        ForeColor = Color.White
        Icon = CType(resources.GetObject("$this.Icon"), Icon)
        Name = "Descripcion"
        StartPosition = FormStartPosition.CenterScreen
        Text = "Tus Switches / Productos / Descripción"
        ResumeLayout(False)
        PerformLayout()
    End Sub
    Friend WithEvents Label2 As Label
    Friend WithEvents Label3 As Label
    Friend WithEvents Label4 As Label
    Friend WithEvents fuerza As Label
    Friend WithEvents tipo As Label
    Friend WithEvents vida As Label
    Friend WithEvents personalizacion As Label
End Class
