# -*- coding: utf-8 -*-
import datetime
from odoo import models, fields, api
from odoo.exceptions import ValidationError
from odoo import _
import logging

_logger = logging.getLogger(__name__)

class task(models.Model):
     _name = 'manage.task'
     _description = 'manage.task'
     
     project = fields.Many2one("manage.project", related = "history.project", readonly = True)
     name = fields.Char(string="Nombre",readonly=False, required=True, help="lntroduzca el nombre")
     description = fields.Text()
     code = fields.Char(compute="_get_code")
     history = fields.Many2one("manage.history")
#     creation_date = fields.Date()
     start_date = fields.Datetime()
     end_date = fields.Datetime()
     is_paused = fields.Boolean()
     
     sprint = fields.Many2one("manage.sprint", compute = "_get_sprint")
     
     technology = fields.Many2many("manage.technology")
     
     def _get_code(self):
          for task in self:
               try:
                    task.code = "TSK_" + str(task.id)
                    _logger.debug("Codigo generado: " + task.code)
               except:
                    raise ValidationError(_("Creacion de codigo errónea"))
     
     @api.depends('code')
     def _get_sprint(self):
          for task in self:
               sprints = self.env['manage.sprint'].search([('project.id', '=', task.history.project.id)])
               found = False
               for sprint in sprints:
                    if isinstance(sprint.end_date, datetime.datetime) and sprint.end_date > datetime.datetime.now():
                         task.sprint = sprint.id
                         found = True
               if not found:
                    task.sprint = False
     def _get_definition_date(self):
          return datetime.datetime.now()
     definition_date = fields.Datetime(default = _get_definition_date)

class sprint(models.Model):
     _name = 'manage.sprint'
     _description = 'manage.sprint'
     project = fields.Many2one("manage.project")
     name = fields.Char()
     description = fields.Text()
     duration = fields.Integer(default = 15)
     start_date = fields.Datetime()
     end_date = fields.Datetime(compute='_get_end_date', store=True)
     task = fields.One2many("manage.task", "sprint")
     @api.depends('start_date', 'duration')
     def _get_end_date(self):
          for sprint in self:
               if isinstance(sprint.start_date, datetime.datetime) and sprint.duration > 0:
                    sprint.end_date = sprint.start_date+ datetime.timedelta(days=sprint.duration)
               else:
                    sprint.end_date = sprint.start_date

class technology(models.Model):
     _name = 'manage.technology'
     _description = 'manage.technology'
     name = fields.Char()
     description = fields.Text()
     photo = fields.Image(max_width=200, max_height=200)

class project(models.Model):
     _name = 'manage.project'
     _description = 'manage.project'
     name = fields.Char()
     description = fields.Text()
     histories = fields.One2many(comodel_name='manage.history', inverse_name = 'project')

class history(models.Model):
     _name = 'manage.history'
     _description = 'manage.history'
     name = fields.Char()
     description = fields.Text()
     project = fields.Many2one('manage.project')
     tasks = fields.One2many(comodel_name='manage.task', inverse_name = 'history')
     
     used_technologies = fields.Many2many("manage.technology", compute = "_get_used_technologies")
     def _get_used_technologies(self):
          for history in self:
               technologies = None
               for task in history.tasks:
                    if not technologies:
                         technologies = task.technology
                    else:
                         technologies = technologies + task.technology
               history.used_technologies = technologies
               
class developer(models.Model):
     _name = 'res.partner'
     _inherit = 'res.partner'

class bancos(models.Model):
     _name = 'res.bank'
     _inherit = 'res.bank'