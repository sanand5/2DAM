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
        numa_CB = New ComboBox()
        numb_CB = New ComboBox()
        res_lbl = New Label()
        calc_bt = New Button()
        Button1 = New Button()
        SuspendLayout()
        ' 
        ' numa_CB
        ' 
        numa_CB.FormattingEnabled = True
        numa_CB.Location = New Point(38, 30)
        numa_CB.Name = "numa_CB"
        numa_CB.Size = New Size(121, 23)
        numa_CB.TabIndex = 0
        ' 
        ' numb_CB
        ' 
        numb_CB.FormattingEnabled = True
        numb_CB.Location = New Point(38, 84)
        numb_CB.Name = "numb_CB"
        numb_CB.Size = New Size(121, 23)
        numb_CB.TabIndex = 1
        ' 
        ' res_lbl
        ' 
        res_lbl.AutoSize = True
        res_lbl.Font = New Font("Segoe UI", 15F, FontStyle.Regular, GraphicsUnit.Point)
        res_lbl.Location = New Point(52, 207)
        res_lbl.Name = "res_lbl"
        res_lbl.Size = New Size(98, 28)
        res_lbl.TabIndex = 2
        res_lbl.Text = "Resultado"
        ' 
        ' calc_bt
        ' 
        calc_bt.BackColor = SystemColors.ActiveCaption
        calc_bt.ForeColor = Color.IndianRed
        calc_bt.Location = New Point(66, 153)
        calc_bt.Name = "calc_bt"
        calc_bt.Size = New Size(75, 23)
        calc_bt.TabIndex = 3
        calc_bt.Text = "Calcular"
        calc_bt.UseVisualStyleBackColor = False
        ' 
        ' Button1
        ' 
        Button1.BackColor = Color.Red
        Button1.ForeColor = Color.Black
        Button1.Location = New Point(148, 238)
        Button1.Name = "Button1"
        Button1.Size = New Size(46, 23)
        Button1.TabIndex = 4
        Button1.Text = "Exit"
        Button1.UseVisualStyleBackColor = False
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        BackColor = Color.Yellow
        ClientSize = New Size(197, 265)
        Controls.Add(Button1)
        Controls.Add(calc_bt)
        Controls.Add(res_lbl)
        Controls.Add(numb_CB)
        Controls.Add(numa_CB)
        Name = "Form1"
        Text = "Calculadora"
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents numa_CB As ComboBox
    Friend WithEvents numb_CB As ComboBox
    Friend WithEvents res_lbl As Label
    Friend WithEvents calc_bt As Button
    Friend WithEvents Button1 As Button
End Class
