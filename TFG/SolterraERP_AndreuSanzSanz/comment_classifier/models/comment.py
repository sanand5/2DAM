from odoo import models, fields, api
from odoo.exceptions import ValidationError

class Comment(models.Model):
    _name = 'comment.classifier.comment'
    _description = 'Comment'

    name = fields.Char(string='Comment', required=True)
    tag_ids = fields.Many2many('comment.classifier.tag', string='Tags', domain="[('dataset_id', '=', dataset_id)]")
    dataset_id = fields.Many2one('comment.classifier.dataset', string='Dataset', required=True)

    @api.constrains('dataset_id')
    def _check_dataset_id(self):
        if not self.dataset_id:
            raise ValidationError("Each comment must be assigned to a dataset.")

class Dataset(models.Model):
    _name = 'comment.classifier.dataset'
    _description = 'Dataset'

    name = fields.Char(string='Dataset Name', required=True)
    comment_ids = fields.One2many('comment.classifier.comment', 'dataset_id', string='Comments')
    tag_ids = fields.One2many('comment.classifier.tag', 'dataset_id', string='Tags')
