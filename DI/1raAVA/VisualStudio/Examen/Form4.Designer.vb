<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form4
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
        Dim resources As ComponentModel.ComponentResourceManager = New ComponentModel.ComponentResourceManager(GetType(Form4))
        GroupBox1 = New GroupBox()
        RadioButtonPlaya = New RadioButton()
        RadioButtonVal = New RadioButton()
        RadioButtonbeni = New RadioButton()
        RadioButtonterra = New RadioButton()
        Label1 = New Label()
        terramitica = New PictureBox()
        benidorm = New PictureBox()
        valencia = New PictureBox()
        playa = New PictureBox()
        GroupBox1.SuspendLayout()
        CType(terramitica, ComponentModel.ISupportInitialize).BeginInit()
        CType(benidorm, ComponentModel.ISupportInitialize).BeginInit()
        CType(valencia, ComponentModel.ISupportInitialize).BeginInit()
        CType(playa, ComponentModel.ISupportInitialize).BeginInit()
        SuspendLayout()
        ' 
        ' GroupBox1
        ' 
        GroupBox1.Controls.Add(RadioButtonPlaya)
        GroupBox1.Controls.Add(RadioButtonVal)
        GroupBox1.Controls.Add(RadioButtonbeni)
        GroupBox1.Controls.Add(RadioButtonterra)
        GroupBox1.Font = New Font("Rockwell Condensed", 15F, FontStyle.Regular, GraphicsUnit.Point)
        GroupBox1.Location = New Point(12, 77)
        GroupBox1.Name = "GroupBox1"
        GroupBox1.Size = New Size(160, 257)
        GroupBox1.TabIndex = 0
        GroupBox1.TabStop = False
        GroupBox1.Text = "Lugares"
        ' 
        ' RadioButtonPlaya
        ' 
        RadioButtonPlaya.AutoSize = True
        RadioButtonPlaya.Font = New Font("Rockwell Condensed", 20F, FontStyle.Regular, GraphicsUnit.Point)
        RadioButtonPlaya.Location = New Point(6, 214)
        RadioButtonPlaya.Name = "RadioButtonPlaya"
        RadioButtonPlaya.Size = New Size(79, 35)
        RadioButtonPlaya.TabIndex = 4
        RadioButtonPlaya.TabStop = True
        RadioButtonPlaya.Text = "Playa"
        RadioButtonPlaya.UseVisualStyleBackColor = True
        ' 
        ' RadioButtonVal
        ' 
        RadioButtonVal.AutoSize = True
        RadioButtonVal.Font = New Font("Rockwell Condensed", 20F, FontStyle.Regular, GraphicsUnit.Point)
        RadioButtonVal.Location = New Point(6, 150)
        RadioButtonVal.Name = "RadioButtonVal"
        RadioButtonVal.Size = New Size(103, 35)
        RadioButtonVal.TabIndex = 3
        RadioButtonVal.TabStop = True
        RadioButtonVal.Text = "Valencia"
        RadioButtonVal.UseVisualStyleBackColor = True
        ' 
        ' RadioButtonbeni
        ' 
        RadioButtonbeni.AutoSize = True
        RadioButtonbeni.Font = New Font("Rockwell Condensed", 20F, FontStyle.Regular, GraphicsUnit.Point)
        RadioButtonbeni.Location = New Point(6, 86)
        RadioButtonbeni.Name = "RadioButtonbeni"
        RadioButtonbeni.Size = New Size(111, 35)
        RadioButtonbeni.TabIndex = 2
        RadioButtonbeni.TabStop = True
        RadioButtonbeni.Text = "Benidorm"
        RadioButtonbeni.UseVisualStyleBackColor = True
        ' 
        ' RadioButtonterra
        ' 
        RadioButtonterra.AutoSize = True
        RadioButtonterra.Font = New Font("Rockwell Condensed", 20F, FontStyle.Regular, GraphicsUnit.Point)
        RadioButtonterra.Location = New Point(6, 22)
        RadioButtonterra.Name = "RadioButtonterra"
        RadioButtonterra.Size = New Size(142, 35)
        RadioButtonterra.TabIndex = 1
        RadioButtonterra.TabStop = True
        RadioButtonterra.Text = "Terra Mítica"
        RadioButtonterra.UseVisualStyleBackColor = True
        ' 
        ' Label1
        ' 
        Label1.AutoSize = True
        Label1.Font = New Font("Rockwell Condensed", 30F, FontStyle.Regular, GraphicsUnit.Point)
        Label1.Location = New Point(18, 21)
        Label1.Name = "Label1"
        Label1.Size = New Size(177, 47)
        Label1.TabIndex = 1
        Label1.Text = "Ubicaciones:"
        ' 
        ' terramitica
        ' 
        terramitica.Image = CType(resources.GetObject("terramitica.Image"), Image)
        terramitica.Location = New Point(241, 84)
        terramitica.Name = "terramitica"
        terramitica.Size = New Size(314, 242)
        terramitica.SizeMode = PictureBoxSizeMode.CenterImage
        terramitica.TabIndex = 2
        terramitica.TabStop = False
        ' 
        ' benidorm
        ' 
        benidorm.Image = CType(resources.GetObject("benidorm.Image"), Image)
        benidorm.Location = New Point(241, 84)
        benidorm.Name = "benidorm"
        benidorm.Size = New Size(314, 242)
        benidorm.SizeMode = PictureBoxSizeMode.CenterImage
        benidorm.TabIndex = 3
        benidorm.TabStop = False
        ' 
        ' valencia
        ' 
        valencia.Image = CType(resources.GetObject("valencia.Image"), Image)
        valencia.Location = New Point(241, 84)
        valencia.Name = "valencia"
        valencia.Size = New Size(314, 242)
        valencia.SizeMode = PictureBoxSizeMode.CenterImage
        valencia.TabIndex = 4
        valencia.TabStop = False
        ' 
        ' playa
        ' 
        playa.Image = CType(resources.GetObject("playa.Image"), Image)
        playa.Location = New Point(241, 84)
        playa.Name = "playa"
        playa.Size = New Size(314, 242)
        playa.SizeMode = PictureBoxSizeMode.CenterImage
        playa.TabIndex = 5
        playa.TabStop = False
        ' 
        ' Form4
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(577, 346)
        Controls.Add(playa)
        Controls.Add(valencia)
        Controls.Add(benidorm)
        Controls.Add(terramitica)
        Controls.Add(Label1)
        Controls.Add(GroupBox1)
        Icon = CType(resources.GetObject("$this.Icon"), Icon)
        Name = "Form4"
        Text = "Ubicaciones"
        GroupBox1.ResumeLayout(False)
        GroupBox1.PerformLayout()
        CType(terramitica, ComponentModel.ISupportInitialize).EndInit()
        CType(benidorm, ComponentModel.ISupportInitialize).EndInit()
        CType(valencia, ComponentModel.ISupportInitialize).EndInit()
        CType(playa, ComponentModel.ISupportInitialize).EndInit()
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents GroupBox1 As GroupBox
    Friend WithEvents RadioButtonPlaya As RadioButton
    Friend WithEvents RadioButtonVal As RadioButton
    Friend WithEvents RadioButtonbeni As RadioButton
    Friend WithEvents RadioButtonterra As RadioButton
    Friend WithEvents Label1 As Label
    Friend WithEvents terramitica As PictureBox
    Friend WithEvents benidorm As PictureBox
    Friend WithEvents valencia As PictureBox
    Friend WithEvents playa As PictureBox
End Class
