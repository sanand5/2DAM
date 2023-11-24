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
        PictureBox1 = New PictureBox()
        MenuStrip1 = New MenuStrip()
        IToolStripMenuItem = New ToolStripMenuItem()
        ParqueToolStripMenuItem = New ToolStripMenuItem()
        HistoriaToolStripMenuItem = New ToolStripMenuItem()
        UbicacionesToolStripMenuItem = New ToolStripMenuItem()
        EntradasToolStripMenuItem = New ToolStripMenuItem()
        PreciosToolStripMenuItem = New ToolStripMenuItem()
        CalcularEntradasToolStripMenuItem = New ToolStripMenuItem()
        AcercaDeToolStripMenuItem = New ToolStripMenuItem()
        CType(PictureBox1, ComponentModel.ISupportInitialize).BeginInit()
        MenuStrip1.SuspendLayout()
        SuspendLayout()
        ' 
        ' PictureBox1
        ' 
        PictureBox1.BackColor = Color.Transparent
        PictureBox1.Image = CType(resources.GetObject("PictureBox1.Image"), Image)
        PictureBox1.Location = New Point(520, 289)
        PictureBox1.Margin = New Padding(2, 3, 2, 3)
        PictureBox1.Name = "PictureBox1"
        PictureBox1.Size = New Size(47, 46)
        PictureBox1.TabIndex = 0
        PictureBox1.TabStop = False
        ' 
        ' MenuStrip1
        ' 
        MenuStrip1.Items.AddRange(New ToolStripItem() {IToolStripMenuItem, EntradasToolStripMenuItem, AcercaDeToolStripMenuItem})
        MenuStrip1.Location = New Point(0, 0)
        MenuStrip1.Name = "MenuStrip1"
        MenuStrip1.Padding = New Padding(5, 2, 0, 2)
        MenuStrip1.Size = New Size(577, 24)
        MenuStrip1.TabIndex = 1
        MenuStrip1.Text = "MenuStrip1"
        ' 
        ' IToolStripMenuItem
        ' 
        IToolStripMenuItem.DropDownItems.AddRange(New ToolStripItem() {ParqueToolStripMenuItem, HistoriaToolStripMenuItem, UbicacionesToolStripMenuItem})
        IToolStripMenuItem.Font = New Font("Microsoft Sans Serif", 9F, FontStyle.Regular, GraphicsUnit.Point)
        IToolStripMenuItem.Name = "IToolStripMenuItem"
        IToolStripMenuItem.Size = New Size(86, 20)
        IToolStripMenuItem.Text = "&Introducción"
        ' 
        ' ParqueToolStripMenuItem
        ' 
        ParqueToolStripMenuItem.Name = "ParqueToolStripMenuItem"
        ParqueToolStripMenuItem.ShortcutKeys = Keys.Control Or Keys.P
        ParqueToolStripMenuItem.Size = New Size(183, 22)
        ParqueToolStripMenuItem.Text = "Parque"
        ' 
        ' HistoriaToolStripMenuItem
        ' 
        HistoriaToolStripMenuItem.Name = "HistoriaToolStripMenuItem"
        HistoriaToolStripMenuItem.ShortcutKeys = Keys.Control Or Keys.H
        HistoriaToolStripMenuItem.Size = New Size(183, 22)
        HistoriaToolStripMenuItem.Text = "Historia"
        ' 
        ' UbicacionesToolStripMenuItem
        ' 
        UbicacionesToolStripMenuItem.Name = "UbicacionesToolStripMenuItem"
        UbicacionesToolStripMenuItem.ShortcutKeys = Keys.Control Or Keys.U
        UbicacionesToolStripMenuItem.Size = New Size(183, 22)
        UbicacionesToolStripMenuItem.Text = "Ubicaciones"
        ' 
        ' EntradasToolStripMenuItem
        ' 
        EntradasToolStripMenuItem.DropDownItems.AddRange(New ToolStripItem() {PreciosToolStripMenuItem, CalcularEntradasToolStripMenuItem})
        EntradasToolStripMenuItem.Font = New Font("Microsoft Sans Serif", 9F, FontStyle.Regular, GraphicsUnit.Point)
        EntradasToolStripMenuItem.Name = "EntradasToolStripMenuItem"
        EntradasToolStripMenuItem.Size = New Size(68, 20)
        EntradasToolStripMenuItem.Text = "&Entradas"
        ' 
        ' PreciosToolStripMenuItem
        ' 
        PreciosToolStripMenuItem.Name = "PreciosToolStripMenuItem"
        PreciosToolStripMenuItem.Size = New Size(174, 22)
        PreciosToolStripMenuItem.Text = "Precios"
        ' 
        ' CalcularEntradasToolStripMenuItem
        ' 
        CalcularEntradasToolStripMenuItem.Name = "CalcularEntradasToolStripMenuItem"
        CalcularEntradasToolStripMenuItem.Size = New Size(174, 22)
        CalcularEntradasToolStripMenuItem.Text = "Calcula tu Entrada"
        ' 
        ' AcercaDeToolStripMenuItem
        ' 
        AcercaDeToolStripMenuItem.Font = New Font("Microsoft Sans Serif", 9F, FontStyle.Regular, GraphicsUnit.Point)
        AcercaDeToolStripMenuItem.Name = "AcercaDeToolStripMenuItem"
        AcercaDeToolStripMenuItem.Size = New Size(73, 20)
        AcercaDeToolStripMenuItem.Text = "&Acerca de"
        ' 
        ' Form2
        ' 
        AutoScaleDimensions = New SizeF(6F, 14F)
        AutoScaleMode = AutoScaleMode.Font
        BackgroundImage = CType(resources.GetObject("$this.BackgroundImage"), Image)
        ClientSize = New Size(577, 346)
        Controls.Add(PictureBox1)
        Controls.Add(MenuStrip1)
        Font = New Font("Rockwell Condensed", 9F, FontStyle.Bold, GraphicsUnit.Point)
        Icon = CType(resources.GetObject("$this.Icon"), Icon)
        MainMenuStrip = MenuStrip1
        Margin = New Padding(2, 3, 2, 3)
        Name = "Form2"
        Text = "Pantalla Principal"
        CType(PictureBox1, ComponentModel.ISupportInitialize).EndInit()
        MenuStrip1.ResumeLayout(False)
        MenuStrip1.PerformLayout()
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents PictureBox1 As PictureBox
    Friend WithEvents MenuStrip1 As MenuStrip
    Friend WithEvents IToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents EntradasToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents AcercaDeToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents ParqueToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents HistoriaToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents UbicacionesToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents PreciosToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents CalcularEntradasToolStripMenuItem As ToolStripMenuItem
End Class
