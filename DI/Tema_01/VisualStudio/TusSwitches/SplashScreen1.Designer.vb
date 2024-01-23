<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class SplashScreen1
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
        Dim resources As ComponentModel.ComponentResourceManager = New ComponentModel.ComponentResourceManager(GetType(SplashScreen1))
        PictureBox1 = New PictureBox()
        TUSSWITCHES = New Label()
        CType(PictureBox1, ComponentModel.ISupportInitialize).BeginInit()
        SuspendLayout()
        ' 
        ' PictureBox1
        ' 
        PictureBox1.BackColor = Color.Transparent
        PictureBox1.BackgroundImage = CType(resources.GetObject("PictureBox1.BackgroundImage"), Image)
        PictureBox1.BackgroundImageLayout = ImageLayout.Stretch
        PictureBox1.Location = New Point(366, 99)
        PictureBox1.Name = "PictureBox1"
        PictureBox1.Size = New Size(91, 82)
        PictureBox1.TabIndex = 0
        PictureBox1.TabStop = False
        ' 
        ' TUSSWITCHES
        ' 
        TUSSWITCHES.AutoSize = True
        TUSSWITCHES.BackColor = Color.Transparent
        TUSSWITCHES.Font = New Font("Microsoft PhagsPa", 35.25F, FontStyle.Bold, GraphicsUnit.Point)
        TUSSWITCHES.Location = New Point(27, 119)
        TUSSWITCHES.Name = "TUSSWITCHES"
        TUSSWITCHES.Size = New Size(347, 62)
        TUSSWITCHES.TabIndex = 2
        TUSSWITCHES.Text = "TUSSWITCHES"
        ' 
        ' SplashScreen1
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        BackColor = Color.Black
        ClientSize = New Size(498, 298)
        ControlBox = False
        Controls.Add(TUSSWITCHES)
        Controls.Add(PictureBox1)
        ForeColor = Color.Cyan
        FormBorderStyle = FormBorderStyle.FixedSingle
        MaximizeBox = False
        MinimizeBox = False
        Name = "SplashScreen1"
        ShowInTaskbar = False
        StartPosition = FormStartPosition.CenterScreen
        CType(PictureBox1, ComponentModel.ISupportInitialize).EndInit()
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents PictureBox1 As PictureBox
    Friend WithEvents TUSSWITCHES As Label
End Class
