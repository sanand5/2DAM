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
        prnum_lbl = New Label()
        mas_lbl = New Label()
        sgnum_lbl = New Label()
        res_lbl = New Label()
        res_bt = New Button()
        cls_btn = New Button()
        num_tb = New TextBox()
        num2_tb = New TextBox()
        SuspendLayout()
        ' 
        ' prnum_lbl
        ' 
        prnum_lbl.AutoSize = True
        prnum_lbl.Font = New Font("Segoe UI", 16F, FontStyle.Regular, GraphicsUnit.Point)
        prnum_lbl.Location = New Point(25, 32)
        prnum_lbl.Margin = New Padding(5, 0, 5, 0)
        prnum_lbl.Name = "prnum_lbl"
        prnum_lbl.Size = New Size(159, 30)
        prnum_lbl.TabIndex = 0
        prnum_lbl.Text = "Primer número"
        ' 
        ' mas_lbl
        ' 
        mas_lbl.AutoSize = True
        mas_lbl.Font = New Font("Segoe UI", 16F, FontStyle.Regular, GraphicsUnit.Point)
        mas_lbl.Location = New Point(25, 125)
        mas_lbl.Margin = New Padding(5, 0, 5, 0)
        mas_lbl.Name = "mas_lbl"
        mas_lbl.Size = New Size(28, 30)
        mas_lbl.TabIndex = 1
        mas_lbl.Text = "+"
        ' 
        ' sgnum_lbl
        ' 
        sgnum_lbl.AutoSize = True
        sgnum_lbl.Font = New Font("Segoe UI", 16F, FontStyle.Regular, GraphicsUnit.Point)
        sgnum_lbl.Location = New Point(25, 176)
        sgnum_lbl.Margin = New Padding(5, 0, 5, 0)
        sgnum_lbl.Name = "sgnum_lbl"
        sgnum_lbl.Size = New Size(182, 30)
        sgnum_lbl.TabIndex = 2
        sgnum_lbl.Text = "Segundo número"
        ' 
        ' res_lbl
        ' 
        res_lbl.AutoSize = True
        res_lbl.Font = New Font("Segoe UI", 16F, FontStyle.Regular, GraphicsUnit.Point)
        res_lbl.Location = New Point(25, 284)
        res_lbl.Margin = New Padding(5, 0, 5, 0)
        res_lbl.Name = "res_lbl"
        res_lbl.Size = New Size(107, 30)
        res_lbl.TabIndex = 3
        res_lbl.Text = "Resultado"
        ' 
        ' res_bt
        ' 
        res_bt.Location = New Point(25, 355)
        res_bt.Name = "res_bt"
        res_bt.Size = New Size(131, 44)
        res_bt.TabIndex = 4
        res_bt.Text = "Resultado"
        res_bt.UseVisualStyleBackColor = True
        ' 
        ' cls_btn
        ' 
        cls_btn.Location = New Point(341, 355)
        cls_btn.Name = "cls_btn"
        cls_btn.Size = New Size(131, 44)
        cls_btn.TabIndex = 6
        cls_btn.Text = "Cerrar"
        cls_btn.UseVisualStyleBackColor = True
        ' 
        ' num_tb
        ' 
        num_tb.Location = New Point(32, 65)
        num_tb.Name = "num_tb"
        num_tb.Size = New Size(152, 36)
        num_tb.TabIndex = 7
        ' 
        ' num2_tb
        ' 
        num2_tb.Location = New Point(32, 209)
        num2_tb.Name = "num2_tb"
        num2_tb.Size = New Size(152, 36)
        num2_tb.TabIndex = 8
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(12F, 30F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(484, 411)
        Controls.Add(num2_tb)
        Controls.Add(num_tb)
        Controls.Add(cls_btn)
        Controls.Add(res_bt)
        Controls.Add(res_lbl)
        Controls.Add(sgnum_lbl)
        Controls.Add(mas_lbl)
        Controls.Add(prnum_lbl)
        Font = New Font("Segoe UI", 16F, FontStyle.Regular, GraphicsUnit.Point)
        Margin = New Padding(5, 6, 5, 6)
        Name = "Form1"
        Text = "Calculadora Simple"
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents prnum_lbl As Label
    Friend WithEvents mas_lbl As Label
    Friend WithEvents sgnum_lbl As Label
    Friend WithEvents res_lbl As Label
    Friend WithEvents res_bt As Button
    Friend WithEvents cls_btn As Button
    Friend WithEvents num_tb As TextBox
    Friend WithEvents num2_tb As TextBox
End Class
