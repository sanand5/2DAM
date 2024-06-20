# -*- coding: utf-8 -*-
# from odoo import http


# class Gandibandreu(http.Controller):
#     @http.route('/gandibandreu/gandibandreu', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/gandibandreu/gandibandreu/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('gandibandreu.listing', {
#             'root': '/gandibandreu/gandibandreu',
#             'objects': http.request.env['gandibandreu.gandibandreu'].search([]),
#         })

#     @http.route('/gandibandreu/gandibandreu/objects/<model("gandibandreu.gandibandreu"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('gandibandreu.object', {
#             'object': obj
#         })
