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
        rb_letter = New RadioButton()
        GroupBox1 = New GroupBox()
        rb_num = New RadioButton()
        rb_all = New RadioButton()
        lb_upper = New Label()
        lb_lower = New Label()
        lb_num = New Label()
        btn_close = New Button()
        btn_bgcolor = New Button()
        MenuStrip1 = New MenuStrip()
        FIleºToolStripMenuItem = New ToolStripMenuItem()
        mn_exit = New ToolStripMenuItem()
        ColorToolStripMenuItem = New ToolStripMenuItem()
        mn_bgcolor = New ToolStripMenuItem()
        mn_fcolor = New ToolStripMenuItem()
        HelpToolStripMenuItem = New ToolStripMenuItem()
        ViewHelpToolStripMenuItem = New ToolStripMenuItem()
        AboutToolStripMenuItem = New ToolStripMenuItem()
        btn_fgcolor = New Button()
        ColorDialog1 = New ColorDialog()
        GroupBox1.SuspendLayout()
        MenuStrip1.SuspendLayout()
        SuspendLayout()
        ' 
        ' rb_letter
        ' 
        rb_letter.AutoSize = True
        rb_letter.Checked = True
        rb_letter.Font = New Font("Microsoft Sans Serif", 8.25F, FontStyle.Regular, GraphicsUnit.Point)
        rb_letter.Location = New Point(6, 22)
        rb_letter.Name = "rb_letter"
        rb_letter.Size = New Size(84, 17)
        rb_letter.TabIndex = 0
        rb_letter.TabStop = True
        rb_letter.Text = "Letter:    A-Z"
        rb_letter.UseVisualStyleBackColor = True
        ' 
        ' GroupBox1
        ' 
        GroupBox1.Controls.Add(rb_num)
        GroupBox1.Controls.Add(rb_all)
        GroupBox1.Controls.Add(rb_letter)
        GroupBox1.Location = New Point(12, 25)
        GroupBox1.Name = "GroupBox1"
        GroupBox1.Size = New Size(134, 100)
        GroupBox1.TabIndex = 1
        GroupBox1.TabStop = False
        GroupBox1.Tag = ""
        ' 
        ' rb_num
        ' 
        rb_num.AutoSize = True
        rb_num.Font = New Font("Microsoft Sans Serif", 8.25F, FontStyle.Regular, GraphicsUnit.Point)
        rb_num.Location = New Point(6, 68)
        rb_num.Name = "rb_num"
        rb_num.Size = New Size(89, 17)
        rb_num.TabIndex = 2
        rb_num.Text = "Number: 0 - 9"
        rb_num.UseVisualStyleBackColor = True
        ' 
        ' rb_all
        ' 
        rb_all.AutoSize = True
        rb_all.Font = New Font("Microsoft Sans Serif", 8.25F, FontStyle.Regular, GraphicsUnit.Point)
        rb_all.Location = New Point(6, 45)
        rb_all.Name = "rb_all"
        rb_all.Size = New Size(119, 17)
        rb_all.TabIndex = 1
        rb_all.Text = "All:          A - Z, 0 - 9"
        rb_all.UseVisualStyleBackColor = True
        ' 
        ' lb_upper
        ' 
        lb_upper.AutoSize = True
        lb_upper.Font = New Font("Segoe UI", 13F, FontStyle.Regular, GraphicsUnit.Point)
        lb_upper.Location = New Point(18, 138)
        lb_upper.Name = "lb_upper"
        lb_upper.Size = New Size(107, 25)
        lb_upper.TabIndex = 2
        lb_upper.Text = "Upper Case:"
        ' 
        ' lb_lower
        ' 
        lb_lower.AutoSize = True
        lb_lower.Font = New Font("Segoe UI", 13F, FontStyle.Regular, GraphicsUnit.Point)
        lb_lower.Location = New Point(18, 185)
        lb_lower.Name = "lb_lower"
        lb_lower.Size = New Size(105, 25)
        lb_lower.TabIndex = 3
        lb_lower.Text = "Lower Case:"
        ' 
        ' lb_num
        ' 
        lb_num.AutoSize = True
        lb_num.Font = New Font("Segoe UI", 13F, FontStyle.Regular, GraphicsUnit.Point)
        lb_num.Location = New Point(18, 163)
        lb_num.Name = "lb_num"
        lb_num.Size = New Size(86, 25)
        lb_num.TabIndex = 4
        lb_num.Text = "Number: "
        lb_num.Visible = False
        ' 
        ' btn_close
        ' 
        btn_close.Font = New Font("Segoe UI", 11F, FontStyle.Regular, GraphicsUnit.Point)
        btn_close.Location = New Point(204, 205)
        btn_close.Name = "btn_close"
        btn_close.Size = New Size(75, 30)
        btn_close.TabIndex = 4
        btn_close.Tag = ""
        btn_close.Text = "Quit"
        btn_close.UseVisualStyleBackColor = True
        ' 
        ' btn_bgcolor
        ' 
        btn_bgcolor.AccessibleName = "btn_bgcolor"
        btn_bgcolor.Font = New Font("Segoe UI", 11F, FontStyle.Regular, GraphicsUnit.Point)
        btn_bgcolor.Location = New Point(154, 32)
        btn_bgcolor.Name = "btn_bgcolor"
        btn_bgcolor.Size = New Size(125, 28)
        btn_bgcolor.TabIndex = 2
        btn_bgcolor.Tag = ""
        btn_bgcolor.Text = "Background"
        btn_bgcolor.UseVisualStyleBackColor = True
        ' 
        ' MenuStrip1
        ' 
        MenuStrip1.Items.AddRange(New ToolStripItem() {FIleºToolStripMenuItem, ColorToolStripMenuItem, HelpToolStripMenuItem})
        MenuStrip1.Location = New Point(0, 0)
        MenuStrip1.Name = "MenuStrip1"
        MenuStrip1.Size = New Size(289, 24)
        MenuStrip1.TabIndex = 8
        MenuStrip1.Text = "MenuStrip1"
        ' 
        ' FIleºToolStripMenuItem
        ' 
        FIleºToolStripMenuItem.DropDownItems.AddRange(New ToolStripItem() {mn_exit})
        FIleºToolStripMenuItem.Name = "FIleºToolStripMenuItem"
        FIleºToolStripMenuItem.Size = New Size(37, 20)
        FIleºToolStripMenuItem.Text = "&File"
        ' 
        ' mn_exit
        ' 
        mn_exit.Name = "mn_exit"
        mn_exit.ShortcutKeys = Keys.Alt Or Keys.F4
        mn_exit.Size = New Size(135, 22)
        mn_exit.Text = "Exit"
        ' 
        ' ColorToolStripMenuItem
        ' 
        ColorToolStripMenuItem.DropDownItems.AddRange(New ToolStripItem() {mn_bgcolor, mn_fcolor})
        ColorToolStripMenuItem.Name = "ColorToolStripMenuItem"
        ColorToolStripMenuItem.Size = New Size(48, 20)
        ColorToolStripMenuItem.Text = "&Color"
        ' 
        ' mn_bgcolor
        ' 
        mn_bgcolor.Name = "mn_bgcolor"
        mn_bgcolor.ShortcutKeys = Keys.Control Or Keys.B
        mn_bgcolor.Size = New Size(211, 22)
        mn_bgcolor.Text = "Background Color"
        ' 
        ' mn_fcolor
        ' 
        mn_fcolor.Name = "mn_fcolor"
        mn_fcolor.ShortcutKeys = Keys.Control Or Keys.F
        mn_fcolor.Size = New Size(211, 22)
        mn_fcolor.Text = "Font Color"
        ' 
        ' HelpToolStripMenuItem
        ' 
        HelpToolStripMenuItem.DropDownItems.AddRange(New ToolStripItem() {ViewHelpToolStripMenuItem, AboutToolStripMenuItem})
        HelpToolStripMenuItem.Name = "HelpToolStripMenuItem"
        HelpToolStripMenuItem.Size = New Size(44, 20)
        HelpToolStripMenuItem.Text = "&Help"
        ' 
        ' ViewHelpToolStripMenuItem
        ' 
        ViewHelpToolStripMenuItem.Name = "ViewHelpToolStripMenuItem"
        ViewHelpToolStripMenuItem.ShortcutKeys = Keys.Control Or Keys.F1
        ViewHelpToolStripMenuItem.Size = New Size(219, 22)
        ViewHelpToolStripMenuItem.Text = "View Help"
        ' 
        ' AboutToolStripMenuItem
        ' 
        AboutToolStripMenuItem.Name = "AboutToolStripMenuItem"
        AboutToolStripMenuItem.ShortcutKeys = Keys.Control Or Keys.Shift Or Keys.A
        AboutToolStripMenuItem.Size = New Size(219, 22)
        AboutToolStripMenuItem.Text = "About"
        ' 
        ' btn_fgcolor
        ' 
        btn_fgcolor.AccessibleName = "btn_fcolor"
        btn_fgcolor.Font = New Font("Segoe UI", 11F, FontStyle.Regular, GraphicsUnit.Point)
        btn_fgcolor.Location = New Point(154, 66)
        btn_fgcolor.Name = "btn_fgcolor"
        btn_fgcolor.Size = New Size(125, 28)
        btn_fgcolor.TabIndex = 3
        btn_fgcolor.Tag = ""
        btn_fgcolor.Text = "Font Color"
        btn_fgcolor.UseVisualStyleBackColor = True
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(289, 245)
        Controls.Add(btn_fgcolor)
        Controls.Add(btn_bgcolor)
        Controls.Add(btn_close)
        Controls.Add(lb_num)
        Controls.Add(lb_lower)
        Controls.Add(lb_upper)
        Controls.Add(GroupBox1)
        Controls.Add(MenuStrip1)
        Icon = CType(resources.GetObject("$this.Icon"), Icon)
        MainMenuStrip = MenuStrip1
        Name = "Form1"
        StartPosition = FormStartPosition.CenterScreen
        Text = "Actividad 13"
        GroupBox1.ResumeLayout(False)
        GroupBox1.PerformLayout()
        MenuStrip1.ResumeLayout(False)
        MenuStrip1.PerformLayout()
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents rb_letter As RadioButton
    Friend WithEvents GroupBox1 As GroupBox
    Friend WithEvents rb_num As RadioButton
    Friend WithEvents rb_all As RadioButton
    Friend WithEvents lb_upper As Label
    Friend WithEvents lb_lower As Label
    Friend WithEvents lb_num As Label
    Friend WithEvents btn_close As Button
    Friend WithEvents btn_bgcolor As Button
    Friend WithEvents MenuStrip1 As MenuStrip
    Friend WithEvents FIleºToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents mn_exit As ToolStripMenuItem
    Friend WithEvents ColorToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents HelpToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents mn_bgcolor As ToolStripMenuItem
    Friend WithEvents mn_fcolor As ToolStripMenuItem
    Friend WithEvents btn_fgcolor As Button
    Friend WithEvents ColorDialog1 As ColorDialog
    Friend WithEvents ViewHelpToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents AboutToolStripMenuItem As ToolStripMenuItem
End Class
