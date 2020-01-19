import { Component, OnInit, Input } from '@angular/core';
import { Funcionario } from '../../../models/funcionarios';

@Component({
  selector: 'app-funcionario-form-full',
  templateUrl: './form-full.component.html',
  styleUrls: ['./form-full.component.css']
})
export class FuncionarioFormFullComponent implements OnInit {

  @Input() readonly: boolean;
  @Input() funcionario: Funcionario;

  constructor() { }

  ngOnInit() {
  }

}
