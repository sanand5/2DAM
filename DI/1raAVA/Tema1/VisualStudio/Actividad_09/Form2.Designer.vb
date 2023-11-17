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
        bt_cl = New Button()
        bt_add = New Button()
        lbl_form2 = New Label()
        SuspendLayout()
        ' 
        ' bt_cl
        ' 
        bt_cl.Font = New Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point)
        bt_cl.Location = New Point(62, 146)
        bt_cl.Name = "bt_cl"
        bt_cl.Size = New Size(95, 33)
        bt_cl.TabIndex = 0
        bt_cl.Text = "Limpiar"
        bt_cl.UseVisualStyleBackColor = True
        ' 
        ' bt_add
        ' 
        bt_add.Font = New Font("Segoe UI", 15F, FontStyle.Regular, GraphicsUnit.Point)
        bt_add.Location = New Point(56, 86)
        bt_add.Name = "bt_add"
        bt_add.Size = New Size(106, 54)
        bt_add.TabIndex = 1
        bt_add.Text = "Añadir"
        bt_add.UseVisualStyleBackColor = True
        ' 
        ' lbl_form2
        ' 
        lbl_form2.AutoSize = True
        lbl_form2.Font = New Font("Segoe UI", 20F, FontStyle.Bold, GraphicsUnit.Point)
        lbl_form2.Location = New Point(19, 25)
        lbl_form2.Name = "lbl_form2"
        lbl_form2.Size = New Size(181, 37)
        lbl_form2.TabIndex = 2
        lbl_form2.Text = "Folmulario II"
        ' 
        ' Form2
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(218, 212)
        Controls.Add(lbl_form2)
        Controls.Add(bt_add)
        Controls.Add(bt_cl)
        Name = "Form2"
        Text = "Form2"
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents bt_cl As Button
    Friend WithEvents bt_add As Button
    Friend WithEvents lbl_form2 As Label
End Class
