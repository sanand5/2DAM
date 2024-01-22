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
        inv_txtb = New TextBox()
        btnMostrar = New Button()
        SuspendLayout()
        ' 
        ' inv_txtb
        ' 
        inv_txtb.Font = New Font("Segoe UI", 17F, FontStyle.Regular, GraphicsUnit.Point)
        inv_txtb.Location = New Point(12, 12)
        inv_txtb.Multiline = True
        inv_txtb.Name = "inv_txtb"
        inv_txtb.ReadOnly = True
        inv_txtb.ScrollBars = ScrollBars.Vertical
        inv_txtb.Size = New Size(494, 274)
        inv_txtb.TabIndex = 0
        ' 
        ' btnMostrar
        ' 
        btnMostrar.Font = New Font("Segoe UI", 11F, FontStyle.Regular, GraphicsUnit.Point)
        btnMostrar.Location = New Point(188, 292)
        btnMostrar.Name = "btnMostrar"
        btnMostrar.Size = New Size(143, 28)
        btnMostrar.TabIndex = 1
        btnMostrar.Text = "Mostrar Inventario"
        btnMostrar.UseVisualStyleBackColor = True
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(518, 332)
        Controls.Add(btnMostrar)
        Controls.Add(inv_txtb)
        Name = "Form1"
        Text = "INVENTARIO ROPA"
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents inv_txtb As TextBox
    Friend WithEvents btnMostrar As Button
End Class
