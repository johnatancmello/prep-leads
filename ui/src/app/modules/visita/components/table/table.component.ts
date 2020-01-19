import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { VisitaFilter } from '../../models/vistita.filter';
import { Visita } from '../../models/visita';
import { Pessoa } from 'src/app/modules/pessoa/models/pessoa';
import { FilterInterface } from '../../../shared/models/filter-interface';

@Component({
  selector: 'app-visita-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class VisitaTableComponent implements OnInit {

  @Input() visitas: Array<Visita>;
  @Input() filter: FilterInterface = new VisitaFilter();
  @Input() idVisitaSelecionado: number;

  @Output() changePage = new EventEmitter();
  @Output() openDialogDetails = new EventEmitter();

  public displayedColumns: string[] = ['id', 'status', 'pessoa', 'dataDoAgendamento', 'funcionario'];

  constructor() { }

  ngOnInit() {
  }

  public getFirstAndLastName(fullName: string): string {
    return Pessoa.getFirstAndLastName(fullName);
  }

  public getClass(idRow: number): string {
    if (this.idVisitaSelecionado === idRow) {
      return 'mat-primary-light primary-light--active';
    }
  }

}
