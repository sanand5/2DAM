<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class ASCII
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
        sub_btn = New Button()
        cb_text = New TextBox()
        cls_btn = New Button()
        Label1 = New Label()
        Label2 = New Label()
        Label3 = New Label()
        cb_letters = New TextBox()
        cb_num = New TextBox()
        cb_other = New TextBox()
        Label4 = New Label()
        SuspendLayout()
        ' 
        ' sub_btn
        ' 
        sub_btn.Font = New Font("Segoe UI", 13F, FontStyle.Regular, GraphicsUnit.Point)
        sub_btn.Location = New Point(12, 130)
        sub_btn.Name = "sub_btn"
        sub_btn.Size = New Size(97, 34)
        sub_btn.TabIndex = 0
        sub_btn.Text = "Submit"
        sub_btn.UseVisualStyleBackColor = True
        ' 
        ' cb_text
        ' 
        cb_text.Location = New Point(12, 35)
        cb_text.Multiline = True
        cb_text.Name = "cb_text"
        cb_text.Size = New Size(275, 89)
        cb_text.TabIndex = 1
        ' 
        ' cls_btn
        ' 
        cls_btn.Font = New Font("Segoe UI", 13F, FontStyle.Regular, GraphicsUnit.Point)
        cls_btn.Location = New Point(190, 130)
        cls_btn.Name = "cls_btn"
        cls_btn.Size = New Size(97, 34)
        cls_btn.TabIndex = 2
        cls_btn.Text = "Clear"
        cls_btn.UseVisualStyleBackColor = True
        ' 
        ' Label1
        ' 
        Label1.AutoSize = True
        Label1.Font = New Font("Segoe UI", 14F, FontStyle.Bold, GraphicsUnit.Point)
        Label1.Location = New Point(6, 166)
        Label1.Name = "Label1"
        Label1.Size = New Size(65, 25)
        Label1.TabIndex = 3
        Label1.Text = "Letras"
        ' 
        ' Label2
        ' 
        Label2.AutoSize = True
        Label2.Font = New Font("Segoe UI", 14F, FontStyle.Bold, GraphicsUnit.Point)
        Label2.Location = New Point(8, 228)
        Label2.Name = "Label2"
        Label2.Size = New Size(94, 25)
        Label2.TabIndex = 4
        Label2.Text = "Números"
        ' 
        ' Label3
        ' 
        Label3.AutoSize = True
        Label3.Font = New Font("Segoe UI", 14F, FontStyle.Bold, GraphicsUnit.Point)
        Label3.Location = New Point(6, 290)
        Label3.Name = "Label3"
        Label3.Size = New Size(61, 25)
        Label3.TabIndex = 5
        Label3.Text = "Otros"
        ' 
        ' cb_letters
        ' 
        cb_letters.Location = New Point(12, 194)
        cb_letters.Multiline = True
        cb_letters.Name = "cb_letters"
        cb_letters.Size = New Size(275, 31)
        cb_letters.TabIndex = 6
        ' 
        ' cb_num
        ' 
        cb_num.Location = New Point(12, 256)
        cb_num.Multiline = True
        cb_num.Name = "cb_num"
        cb_num.Size = New Size(275, 31)
        cb_num.TabIndex = 7
        ' 
        ' cb_other
        ' 
        cb_other.Location = New Point(12, 318)
        cb_other.Multiline = True
        cb_other.Name = "cb_other"
        cb_other.Size = New Size(275, 31)
        cb_other.TabIndex = 8
        ' 
        ' Label4
        ' 
        Label4.AutoSize = True
        Label4.Font = New Font("Segoe UI", 11F, FontStyle.Regular, GraphicsUnit.Point)
        Label4.Location = New Point(8, 12)
        Label4.Name = "Label4"
        Label4.Size = New Size(198, 20)
        Label4.TabIndex = 9
        Label4.Text = "Inserte Texto para Clasificar: "
        ' 
        ' ASCII
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(299, 360)
        Controls.Add(Label4)
        Controls.Add(cb_other)
        Controls.Add(cb_num)
        Controls.Add(cb_letters)
        Controls.Add(Label3)
        Controls.Add(Label2)
        Controls.Add(Label1)
        Controls.Add(cls_btn)
        Controls.Add(cb_text)
        Controls.Add(sub_btn)
        Name = "ASCII"
        Text = "ASCII"
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents sub_btn As Button
    Friend WithEvents cb_text As TextBox
    Friend WithEvents cls_btn As Button
    Friend WithEvents Label1 As Label
    Friend WithEvents Label2 As Label
    Friend WithEvents Label3 As Label
    Friend WithEvents cb_letters As TextBox
    Friend WithEvents cb_num As TextBox
    Friend WithEvents cb_other As TextBox
    Friend WithEvents Label4 As Label
End Class
