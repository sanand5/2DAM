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
        lbl_char = New Label()
        lbl_num = New Label()
        SuspendLayout()
        ' 
        ' lbl_char
        ' 
        lbl_char.AutoSize = True
        lbl_char.Font = New Font("Rockwell Extra Bold", 30F, FontStyle.Regular, GraphicsUnit.Point)
        lbl_char.Location = New Point(51, 47)
        lbl_char.Name = "lbl_char"
        lbl_char.Size = New Size(136, 47)
        lbl_char.TabIndex = 0
        lbl_char.Text = "Char"
        lbl_char.TextAlign = ContentAlignment.MiddleCenter
        ' 
        ' lbl_num
        ' 
        lbl_num.AutoSize = True
        lbl_num.Font = New Font("Source Sans Pro", 30F, FontStyle.Regular, GraphicsUnit.Point)
        lbl_num.Location = New Point(51, 152)
        lbl_num.Name = "lbl_num"
        lbl_num.Size = New Size(185, 50)
        lbl_num.TabIndex = 1
        lbl_num.Text = "Number : "
        lbl_num.TextAlign = ContentAlignment.MiddleCenter
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(346, 267)
        Controls.Add(lbl_num)
        Controls.Add(lbl_char)
        Name = "Form1"
        Text = "ASCII Conversor"
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents lbl_char As Label
    Friend WithEvents lbl_num As Label
End Class
