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
        lbl_form1 = New Label()
        lbl_cont = New Label()
        btn_openForm2 = New Button()
        SuspendLayout()
        ' 
        ' lbl_form1
        ' 
        lbl_form1.AutoSize = True
        lbl_form1.Font = New Font("Segoe UI", 20F, FontStyle.Bold, GraphicsUnit.Point)
        lbl_form1.Location = New Point(95, 20)
        lbl_form1.Name = "lbl_form1"
        lbl_form1.Size = New Size(172, 37)
        lbl_form1.TabIndex = 0
        lbl_form1.Text = "Folmulario I"
        ' 
        ' lbl_cont
        ' 
        lbl_cont.AutoSize = True
        lbl_cont.Font = New Font("Segoe UI", 15F, FontStyle.Regular, GraphicsUnit.Point)
        lbl_cont.Location = New Point(124, 169)
        lbl_cont.Name = "lbl_cont"
        lbl_cont.Size = New Size(115, 28)
        lbl_cont.TabIndex = 1
        lbl_cont.Text = "Contador: 0"
        ' 
        ' btn_openForm2
        ' 
        btn_openForm2.Font = New Font("Segoe UI", 15F, FontStyle.Regular, GraphicsUnit.Point)
        btn_openForm2.Location = New Point(82, 93)
        btn_openForm2.Name = "btn_openForm2"
        btn_openForm2.Size = New Size(199, 43)
        btn_openForm2.TabIndex = 2
        btn_openForm2.Text = "Abrir Formulario 2"
        btn_openForm2.UseVisualStyleBackColor = True
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(362, 213)
        Controls.Add(btn_openForm2)
        Controls.Add(lbl_cont)
        Controls.Add(lbl_form1)
        Name = "Form1"
        Text = "Form1"
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents lbl_form1 As Label
    Friend WithEvents lbl_cont As Label
    Friend WithEvents btn_openForm2 As Button
End Class
