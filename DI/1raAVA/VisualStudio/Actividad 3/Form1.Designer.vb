<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class Form1
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()>
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        Dim resources As ComponentModel.ComponentResourceManager = New ComponentModel.ComponentResourceManager(GetType(Form1))
        ToolStrip1 = New ToolStrip()
        File_TSL = New ToolStripLabel()
        Tool_TSL = New ToolStripLabel()
        Edit_TLS = New ToolStripLabel()
        see_TSL = New ToolStripLabel()
        pestanas_tp = New TabControl()
        TabPage1 = New TabPage()
        PictureBox1 = New PictureBox()
        TabPage2 = New TabPage()
        PictureBox2 = New PictureBox()
        Tit_lbl = New Label()
        close_bt = New Button()
        img_PB = New PictureBox()
        opt4_rb = New RadioButton()
        opt3_rb = New RadioButton()
        opt2_rb = New RadioButton()
        opt1_rb = New RadioButton()
        unico_CB = New ComboBox()
        txt_tbox = New TextBox()
        ToolStrip1.SuspendLayout()
        pestanas_tp.SuspendLayout()
        TabPage1.SuspendLayout()
        CType(PictureBox1, ComponentModel.ISupportInitialize).BeginInit()
        TabPage2.SuspendLayout()
        CType(PictureBox2, ComponentModel.ISupportInitialize).BeginInit()
        CType(img_PB, ComponentModel.ISupportInitialize).BeginInit()
        SuspendLayout()
        ' 
        ' ToolStrip1
        ' 
        ToolStrip1.Items.AddRange(New ToolStripItem() {File_TSL, Tool_TSL, Edit_TLS, see_TSL})
        ToolStrip1.Location = New Point(0, 0)
        ToolStrip1.Name = "ToolStrip1"
        ToolStrip1.Size = New Size(800, 25)
        ToolStrip1.TabIndex = 0
        ToolStrip1.Text = "ToolStrip1"
        ' 
        ' File_TSL
        ' 
        File_TSL.Name = "File_TSL"
        File_TSL.Size = New Size(53, 22)
        File_TSL.Text = "Archivos"
        ' 
        ' Tool_TSL
        ' 
        Tool_TSL.Name = "Tool_TSL"
        Tool_TSL.Size = New Size(78, 22)
        Tool_TSL.Text = "Herramientas"
        ' 
        ' Edit_TLS
        ' 
        Edit_TLS.Name = "Edit_TLS"
        Edit_TLS.Size = New Size(37, 22)
        Edit_TLS.Text = "Editar"
        ' 
        ' see_TSL
        ' 
        see_TSL.Name = "see_TSL"
        see_TSL.Size = New Size(23, 22)
        see_TSL.Text = "Ver"
        ' 
        ' pestanas_tp
        ' 
        pestanas_tp.Controls.Add(TabPage1)
        pestanas_tp.Controls.Add(TabPage2)
        pestanas_tp.Location = New Point(488, 39)
        pestanas_tp.Name = "pestanas_tp"
        pestanas_tp.SelectedIndex = 0
        pestanas_tp.Size = New Size(265, 286)
        pestanas_tp.TabIndex = 1
        ' 
        ' TabPage1
        ' 
        TabPage1.Controls.Add(PictureBox1)
        TabPage1.Location = New Point(4, 24)
        TabPage1.Name = "TabPage1"
        TabPage1.Padding = New Padding(3)
        TabPage1.Size = New Size(257, 258)
        TabPage1.TabIndex = 0
        TabPage1.Text = "SGE1"
        TabPage1.UseVisualStyleBackColor = True
        ' 
        ' PictureBox1
        ' 
        PictureBox1.Image = CType(resources.GetObject("PictureBox1.Image"), Image)
        PictureBox1.Location = New Point(3, 6)
        PictureBox1.Name = "PictureBox1"
        PictureBox1.Size = New Size(248, 246)
        PictureBox1.TabIndex = 0
        PictureBox1.TabStop = False
        ' 
        ' TabPage2
        ' 
        TabPage2.Controls.Add(PictureBox2)
        TabPage2.Location = New Point(4, 24)
        TabPage2.Name = "TabPage2"
        TabPage2.Padding = New Padding(3)
        TabPage2.Size = New Size(257, 258)
        TabPage2.TabIndex = 1
        TabPage2.Text = "SGE2"
        TabPage2.UseVisualStyleBackColor = True
        ' 
        ' PictureBox2
        ' 
        PictureBox2.Image = CType(resources.GetObject("PictureBox2.Image"), Image)
        PictureBox2.Location = New Point(6, 6)
        PictureBox2.Name = "PictureBox2"
        PictureBox2.Size = New Size(245, 246)
        PictureBox2.TabIndex = 0
        PictureBox2.TabStop = False
        ' 
        ' Tit_lbl
        ' 
        Tit_lbl.AutoSize = True
        Tit_lbl.Font = New Font("Ravie", 20F, FontStyle.Bold Or FontStyle.Italic, GraphicsUnit.Point)
        Tit_lbl.Location = New Point(12, 41)
        Tit_lbl.Name = "Tit_lbl"
        Tit_lbl.Size = New Size(319, 36)
        Tit_lbl.TabIndex = 0
        Tit_lbl.Text = "Hola, bienvenido"
        ' 
        ' close_bt
        ' 
        close_bt.Location = New Point(33, 96)
        close_bt.Name = "close_bt"
        close_bt.Size = New Size(115, 55)
        close_bt.TabIndex = 2
        close_bt.Text = "Exit"
        close_bt.UseVisualStyleBackColor = True
        ' 
        ' img_PB
        ' 
        img_PB.Image = CType(resources.GetObject("img_PB.Image"), Image)
        img_PB.Location = New Point(33, 163)
        img_PB.Name = "img_PB"
        img_PB.Size = New Size(360, 220)
        img_PB.TabIndex = 3
        img_PB.TabStop = False
        ' 
        ' opt4_rb
        ' 
        opt4_rb.AutoSize = True
        opt4_rb.Location = New Point(362, 117)
        opt4_rb.Name = "opt4_rb"
        opt4_rb.Size = New Size(80, 19)
        opt4_rb.TabIndex = 5
        opt4_rb.TabStop = True
        opt4_rb.Text = "Suspender"
        opt4_rb.UseVisualStyleBackColor = True
        ' 
        ' opt3_rb
        ' 
        opt3_rb.AutoSize = True
        opt3_rb.Location = New Point(362, 90)
        opt3_rb.Name = "opt3_rb"
        opt3_rb.Size = New Size(67, 19)
        opt3_rb.TabIndex = 6
        opt3_rb.TabStop = True
        opt3_rb.Text = "Estudiar"
        opt3_rb.UseVisualStyleBackColor = True
        ' 
        ' opt2_rb
        ' 
        opt2_rb.AutoSize = True
        opt2_rb.Location = New Point(362, 65)
        opt2_rb.Name = "opt2_rb"
        opt2_rb.Size = New Size(62, 19)
        opt2_rb.TabIndex = 7
        opt2_rb.TabStop = True
        opt2_rb.Text = "Dormir"
        opt2_rb.UseVisualStyleBackColor = True
        ' 
        ' opt1_rb
        ' 
        opt1_rb.AutoSize = True
        opt1_rb.Location = New Point(362, 41)
        opt1_rb.Name = "opt1_rb"
        opt1_rb.Size = New Size(61, 19)
        opt1_rb.TabIndex = 8
        opt1_rb.TabStop = True
        opt1_rb.Text = "Comer"
        opt1_rb.UseVisualStyleBackColor = True
        ' 
        ' unico_CB
        ' 
        unico_CB.FormattingEnabled = True
        unico_CB.Location = New Point(210, 113)
        unico_CB.Name = "unico_CB"
        unico_CB.Size = New Size(121, 23)
        unico_CB.TabIndex = 9
        ' 
        ' txt_tbox
        ' 
        txt_tbox.Location = New Point(495, 346)
        txt_tbox.Name = "txt_tbox"
        txt_tbox.Size = New Size(225, 23)
        txt_tbox.TabIndex = 10
        txt_tbox.Text = "Escribe aqui tu texto"
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(800, 450)
        Controls.Add(txt_tbox)
        Controls.Add(unico_CB)
        Controls.Add(opt1_rb)
        Controls.Add(opt2_rb)
        Controls.Add(opt3_rb)
        Controls.Add(opt4_rb)
        Controls.Add(img_PB)
        Controls.Add(close_bt)
        Controls.Add(Tit_lbl)
        Controls.Add(pestanas_tp)
        Controls.Add(ToolStrip1)
        Name = "Form1"
        Text = "Form1"
        ToolStrip1.ResumeLayout(False)
        ToolStrip1.PerformLayout()
        pestanas_tp.ResumeLayout(False)
        TabPage1.ResumeLayout(False)
        CType(PictureBox1, ComponentModel.ISupportInitialize).EndInit()
        TabPage2.ResumeLayout(False)
        CType(PictureBox2, ComponentModel.ISupportInitialize).EndInit()
        CType(img_PB, ComponentModel.ISupportInitialize).EndInit()
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents ToolStrip1 As ToolStrip
    Friend WithEvents File_TSL As ToolStripLabel
    Friend WithEvents Tool_TSL As ToolStripLabel
    Friend WithEvents Edit_TLS As ToolStripLabel
    Friend WithEvents see_TSL As ToolStripLabel
    Friend WithEvents pestanas_tp As TabControl
    Friend WithEvents TabPage1 As TabPage
    Friend WithEvents TabPage2 As TabPage
    Friend WithEvents Tit_lbl As Label
    Friend WithEvents close_bt As Button
    Friend WithEvents img_PB As PictureBox
    Friend WithEvents opt4_rb As RadioButton
    Friend WithEvents opt3_rb As RadioButton
    Friend WithEvents opt2_rb As RadioButton
    Friend WithEvents opt1_rb As RadioButton
    Friend WithEvents unico_CB As ComboBox
    Friend WithEvents PictureBox1 As PictureBox
    Friend WithEvents PictureBox2 As PictureBox
    Friend WithEvents txt_tbox As TextBox
End Class
