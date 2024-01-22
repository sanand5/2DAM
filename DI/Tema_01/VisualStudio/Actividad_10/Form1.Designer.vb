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
        tb_calc = New TextBox()
        bt_01 = New Button()
        btn_02 = New Button()
        btn_04 = New Button()
        btn_03 = New Button()
        btn_05 = New Button()
        btn_06 = New Button()
        btn_07 = New Button()
        btn_08 = New Button()
        btn_09 = New Button()
        moneda_lb = New Label()
        lbl_title = New Label()
        Button1 = New Button()
        btn_mas = New Button()
        btn_0 = New Button()
        btn_menos = New Button()
        btn_por = New Button()
        btn_coma = New Button()
        btn_div = New Button()
        btn_igual = New Button()
        SuspendLayout()
        ' 
        ' tb_calc
        ' 
        tb_calc.Font = New Font("Segoe UI", 18F, FontStyle.Regular, GraphicsUnit.Point)
        tb_calc.Location = New Point(20, 39)
        tb_calc.Name = "tb_calc"
        tb_calc.Size = New Size(131, 39)
        tb_calc.TabIndex = 0
        tb_calc.TextAlign = HorizontalAlignment.Right
        ' 
        ' bt_01
        ' 
        bt_01.BackColor = Color.FromArgb(CByte(128), CByte(128), CByte(255))
        bt_01.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        bt_01.Location = New Point(20, 176)
        bt_01.Name = "bt_01"
        bt_01.Size = New Size(40, 40)
        bt_01.TabIndex = 1
        bt_01.Text = "1"
        bt_01.UseVisualStyleBackColor = False
        ' 
        ' btn_02
        ' 
        btn_02.BackColor = Color.FromArgb(CByte(128), CByte(128), CByte(255))
        btn_02.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_02.Location = New Point(66, 176)
        btn_02.Name = "btn_02"
        btn_02.Size = New Size(40, 40)
        btn_02.TabIndex = 2
        btn_02.Text = "2"
        btn_02.UseVisualStyleBackColor = False
        ' 
        ' btn_04
        ' 
        btn_04.BackColor = Color.FromArgb(CByte(128), CByte(128), CByte(255))
        btn_04.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_04.Location = New Point(20, 130)
        btn_04.Name = "btn_04"
        btn_04.Size = New Size(40, 40)
        btn_04.TabIndex = 3
        btn_04.Text = "4"
        btn_04.UseVisualStyleBackColor = False
        ' 
        ' btn_03
        ' 
        btn_03.BackColor = Color.FromArgb(CByte(128), CByte(128), CByte(255))
        btn_03.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_03.Location = New Point(112, 176)
        btn_03.Name = "btn_03"
        btn_03.Size = New Size(40, 40)
        btn_03.TabIndex = 4
        btn_03.Text = "3"
        btn_03.UseVisualStyleBackColor = False
        ' 
        ' btn_05
        ' 
        btn_05.BackColor = Color.FromArgb(CByte(128), CByte(128), CByte(255))
        btn_05.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_05.Location = New Point(66, 130)
        btn_05.Name = "btn_05"
        btn_05.Size = New Size(40, 40)
        btn_05.TabIndex = 5
        btn_05.Text = "5"
        btn_05.UseVisualStyleBackColor = False
        ' 
        ' btn_06
        ' 
        btn_06.BackColor = Color.FromArgb(CByte(128), CByte(128), CByte(255))
        btn_06.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_06.Location = New Point(112, 130)
        btn_06.Name = "btn_06"
        btn_06.Size = New Size(40, 40)
        btn_06.TabIndex = 6
        btn_06.Text = "6"
        btn_06.UseVisualStyleBackColor = False
        ' 
        ' btn_07
        ' 
        btn_07.BackColor = Color.FromArgb(CByte(128), CByte(128), CByte(255))
        btn_07.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_07.Location = New Point(20, 84)
        btn_07.Name = "btn_07"
        btn_07.Size = New Size(40, 40)
        btn_07.TabIndex = 7
        btn_07.Text = "7"
        btn_07.UseVisualStyleBackColor = False
        ' 
        ' btn_08
        ' 
        btn_08.BackColor = Color.FromArgb(CByte(128), CByte(128), CByte(255))
        btn_08.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_08.Location = New Point(66, 84)
        btn_08.Name = "btn_08"
        btn_08.Size = New Size(40, 40)
        btn_08.TabIndex = 8
        btn_08.Text = "8"
        btn_08.UseVisualStyleBackColor = False
        ' 
        ' btn_09
        ' 
        btn_09.BackColor = Color.FromArgb(CByte(128), CByte(128), CByte(255))
        btn_09.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_09.Location = New Point(112, 84)
        btn_09.Name = "btn_09"
        btn_09.Size = New Size(40, 40)
        btn_09.TabIndex = 9
        btn_09.Text = "9"
        btn_09.UseVisualStyleBackColor = False
        ' 
        ' moneda_lb
        ' 
        moneda_lb.AutoSize = True
        moneda_lb.Font = New Font("Segoe UI", 14F, FontStyle.Regular, GraphicsUnit.Point)
        moneda_lb.Location = New Point(180, 42)
        moneda_lb.Name = "moneda_lb"
        moneda_lb.Size = New Size(0, 25)
        moneda_lb.TabIndex = 14
        ' 
        ' lbl_title
        ' 
        lbl_title.AutoSize = True
        lbl_title.Font = New Font("Segoe UI", 15F, FontStyle.Bold, GraphicsUnit.Point)
        lbl_title.Location = New Point(-1, 7)
        lbl_title.Name = "lbl_title"
        lbl_title.Size = New Size(219, 28)
        lbl_title.TabIndex = 15
        lbl_title.Text = "Calculadora Científica"
        ' 
        ' Button1
        ' 
        Button1.BackColor = Color.FromArgb(CByte(255), CByte(128), CByte(128))
        Button1.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        Button1.Location = New Point(157, 38)
        Button1.Name = "Button1"
        Button1.Size = New Size(40, 40)
        Button1.TabIndex = 16
        Button1.Text = "C"
        Button1.UseVisualStyleBackColor = False
        ' 
        ' btn_mas
        ' 
        btn_mas.BackColor = Color.FromArgb(CByte(255), CByte(128), CByte(255))
        btn_mas.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_mas.Location = New Point(157, 84)
        btn_mas.Name = "btn_mas"
        btn_mas.Size = New Size(40, 40)
        btn_mas.TabIndex = 17
        btn_mas.Text = "+"
        btn_mas.UseVisualStyleBackColor = False
        ' 
        ' btn_0
        ' 
        btn_0.BackColor = Color.FromArgb(CByte(128), CByte(128), CByte(255))
        btn_0.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_0.Location = New Point(20, 222)
        btn_0.Name = "btn_0"
        btn_0.Size = New Size(40, 40)
        btn_0.TabIndex = 18
        btn_0.Text = "0"
        btn_0.UseVisualStyleBackColor = False
        ' 
        ' btn_menos
        ' 
        btn_menos.BackColor = Color.FromArgb(CByte(255), CByte(128), CByte(255))
        btn_menos.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_menos.Location = New Point(157, 130)
        btn_menos.Name = "btn_menos"
        btn_menos.Size = New Size(40, 40)
        btn_menos.TabIndex = 19
        btn_menos.Text = "-"
        btn_menos.UseVisualStyleBackColor = False
        ' 
        ' btn_por
        ' 
        btn_por.BackColor = Color.FromArgb(CByte(255), CByte(128), CByte(255))
        btn_por.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_por.Location = New Point(157, 176)
        btn_por.Name = "btn_por"
        btn_por.Size = New Size(40, 40)
        btn_por.TabIndex = 20
        btn_por.Text = "*"
        btn_por.UseVisualStyleBackColor = False
        ' 
        ' btn_coma
        ' 
        btn_coma.BackColor = Color.FromArgb(CByte(255), CByte(128), CByte(255))
        btn_coma.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_coma.Location = New Point(66, 222)
        btn_coma.Name = "btn_coma"
        btn_coma.Size = New Size(40, 40)
        btn_coma.TabIndex = 21
        btn_coma.Text = ","
        btn_coma.UseVisualStyleBackColor = False
        ' 
        ' btn_div
        ' 
        btn_div.BackColor = Color.FromArgb(CByte(255), CByte(128), CByte(255))
        btn_div.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_div.Location = New Point(111, 222)
        btn_div.Name = "btn_div"
        btn_div.Size = New Size(40, 40)
        btn_div.TabIndex = 22
        btn_div.Text = "/"
        btn_div.UseVisualStyleBackColor = False
        ' 
        ' btn_igual
        ' 
        btn_igual.BackColor = Color.FromArgb(CByte(192), CByte(255), CByte(192))
        btn_igual.Font = New Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point)
        btn_igual.Location = New Point(157, 222)
        btn_igual.Name = "btn_igual"
        btn_igual.Size = New Size(40, 40)
        btn_igual.TabIndex = 23
        btn_igual.Text = "="
        btn_igual.UseVisualStyleBackColor = False
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        BackColor = Color.FromArgb(CByte(255), CByte(255), CByte(192))
        ClientSize = New Size(216, 269)
        Controls.Add(btn_igual)
        Controls.Add(btn_div)
        Controls.Add(btn_coma)
        Controls.Add(btn_por)
        Controls.Add(btn_menos)
        Controls.Add(btn_0)
        Controls.Add(btn_mas)
        Controls.Add(Button1)
        Controls.Add(lbl_title)
        Controls.Add(moneda_lb)
        Controls.Add(btn_09)
        Controls.Add(btn_08)
        Controls.Add(btn_07)
        Controls.Add(btn_06)
        Controls.Add(btn_05)
        Controls.Add(btn_03)
        Controls.Add(btn_04)
        Controls.Add(btn_02)
        Controls.Add(bt_01)
        Controls.Add(tb_calc)
        Name = "Form1"
        Text = "Form1"
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents tb_calc As TextBox
    Friend WithEvents bt_01 As Button
    Friend WithEvents btn_02 As Button
    Friend WithEvents btn_04 As Button
    Friend WithEvents btn_03 As Button
    Friend WithEvents btn_05 As Button
    Friend WithEvents btn_06 As Button
    Friend WithEvents btn_07 As Button
    Friend WithEvents btn_08 As Button
    Friend WithEvents btn_09 As Button
    Friend WithEvents moneda_lb As Label
    Friend WithEvents lbl_title As Label
    Friend WithEvents Button1 As Button
    Friend WithEvents btn_mas As Button
    Friend WithEvents btn_0 As Button
    Friend WithEvents btn_menos As Button
    Friend WithEvents btn_por As Button
    Friend WithEvents btn_coma As Button
    Friend WithEvents btn_div As Button
    Friend WithEvents btn_igual As Button
End Class
