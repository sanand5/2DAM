from odoo import models, fields

class Tag(models.Model):
    _name = 'comment.classifier.tag'
    _description = 'Tag'

    name = fields.Char(string='Name', required=True)
    description = fields.Text(string='Description')
    comment_ids = fields.Many2many('comment.classifier.comment', string='Comments')
    dataset_id = fields.Many2one('comment.classifier.dataset', string='Dataset', required=True)
