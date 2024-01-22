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
        dv_rb = New RadioButton()
        ml_rb = New RadioButton()
        rs_rb = New RadioButton()
        sm_rb = New RadioButton()
        gbOperaciones = New GroupBox()
        gbOperaciones.SuspendLayout()
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
        mas_lbl.Size = New Size(0, 30)
        mas_lbl.TabIndex = 1
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
        res_bt.TabIndex = 3
        res_bt.Text = "Resultado"
        res_bt.UseVisualStyleBackColor = True
        ' 
        ' cls_btn
        ' 
        cls_btn.Location = New Point(341, 355)
        cls_btn.Name = "cls_btn"
        cls_btn.Size = New Size(131, 44)
        cls_btn.TabIndex = 5
        cls_btn.Text = "Cerrar"
        cls_btn.UseVisualStyleBackColor = True
        ' 
        ' num_tb
        ' 
        num_tb.Location = New Point(32, 65)
        num_tb.Name = "num_tb"
        num_tb.Size = New Size(152, 36)
        num_tb.TabIndex = 1
        ' 
        ' num2_tb
        ' 
        num2_tb.Location = New Point(32, 209)
        num2_tb.Name = "num2_tb"
        num2_tb.Size = New Size(152, 36)
        num2_tb.TabIndex = 2
        ' 
        ' dv_rb
        ' 
        dv_rb.AutoSize = True
        dv_rb.Location = New Point(9, 239)
        dv_rb.Margin = New Padding(9)
        dv_rb.Name = "dv_rb"
        dv_rb.Padding = New Padding(1)
        dv_rb.Size = New Size(95, 36)
        dv_rb.TabIndex = 4
        dv_rb.Text = "Dividir"
        dv_rb.UseVisualStyleBackColor = True
        ' 
        ' ml_rb
        ' 
        ml_rb.AutoSize = True
        ml_rb.Location = New Point(9, 177)
        ml_rb.Margin = New Padding(9)
        ml_rb.Name = "ml_rb"
        ml_rb.Padding = New Padding(1)
        ml_rb.Size = New Size(134, 36)
        ml_rb.TabIndex = 3
        ml_rb.Text = "Multiplicar"
        ml_rb.UseVisualStyleBackColor = True
        ' 
        ' rs_rb
        ' 
        rs_rb.AutoSize = True
        rs_rb.Location = New Point(9, 115)
        rs_rb.Margin = New Padding(9)
        rs_rb.Name = "rs_rb"
        rs_rb.Padding = New Padding(1)
        rs_rb.Size = New Size(92, 36)
        rs_rb.TabIndex = 2
        rs_rb.Text = "Restar"
        rs_rb.UseVisualStyleBackColor = True
        ' 
        ' sm_rb
        ' 
        sm_rb.AutoSize = True
        sm_rb.Checked = True
        sm_rb.Location = New Point(9, 53)
        sm_rb.Margin = New Padding(9)
        sm_rb.Name = "sm_rb"
        sm_rb.Padding = New Padding(1)
        sm_rb.Size = New Size(95, 36)
        sm_rb.TabIndex = 1
        sm_rb.TabStop = True
        sm_rb.Text = "Sumar"
        sm_rb.UseVisualStyleBackColor = True
        ' 
        ' gbOperaciones
        ' 
        gbOperaciones.Controls.Add(sm_rb)
        gbOperaciones.Controls.Add(dv_rb)
        gbOperaciones.Controls.Add(rs_rb)
        gbOperaciones.Controls.Add(ml_rb)
        gbOperaciones.Location = New Point(245, 32)
        gbOperaciones.Margin = New Padding(0)
        gbOperaciones.Name = "gbOperaciones"
        gbOperaciones.Size = New Size(227, 282)
        gbOperaciones.TabIndex = 4
        gbOperaciones.TabStop = False
        gbOperaciones.Text = "Select An Operation"
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(12F, 30F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(484, 411)
        Controls.Add(gbOperaciones)
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
        gbOperaciones.ResumeLayout(False)
        gbOperaciones.PerformLayout()
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
    Friend WithEvents dv_rb As RadioButton
    Friend WithEvents ml_rb As RadioButton
    Friend WithEvents rs_rb As RadioButton
    Friend WithEvents sm_rb As RadioButton
    Friend WithEvents gbOperaciones As GroupBox
End Class
