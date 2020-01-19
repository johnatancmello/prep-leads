import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { InteresseFilter } from '../../models/interesse.filter';
import { FilterInterface } from '../../../shared/models/filter-interface';
import { Interesse } from '../../models/interesse';
import { Pessoa } from '../../../pessoa/models/pessoa'; // Usado no html

@Component({
  selector: 'app-interesse-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class InteresseTableComponent implements OnInit {

  @Output() changePage = new EventEmitter();
  @Output() refresh = new EventEmitter();
  @Output() openDialogDetails = new EventEmitter();

  @Input() interesses: Array<Interesse>;
  @Input() filter: FilterInterface = new InteresseFilter();
  @Input() idInteresseSelecionado: number;

  public displayedColumns: string[] = ['id', 'status', 'tipo', 'dataDoCadastro', 'funcionario'];

  constructor() {}

  ngOnInit() {
  }

  public getFirstAndLastName(fullName: string): string {
    return Pessoa.getFirstAndLastName(fullName);
  }

  public getClass(idRow: number): string {
    if (this.idInteresseSelecionado === idRow) {
      return 'mat-primary-dark primary-light--active';
    }
  }
}
