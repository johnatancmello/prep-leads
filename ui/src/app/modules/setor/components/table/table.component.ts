import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';

import { MatDialog, MatPaginator } from '@angular/material';

import { SetorDialogComponent } from '../dialog/dialog.component';
import { Setor } from '../../models/setor';

@Component({
  selector: 'app-setor-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class SetorTableComponent implements OnInit {

  @Output() changePage = new EventEmitter();
  @Output() refresh = new EventEmitter();
  @Input() setores: Array<Setor>;
  @Input() length: number;
  @Input() pageSize: number;

  displayedColumns: string[] = ['nome'];

  constructor(
    public dialog: MatDialog,
  ) { }

  ngOnInit() { }

  openDialog(setor: Setor) {
    const dialogRef = this.dialog.open(
      SetorDialogComponent, { data: { setor } }
    );

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.refresh.emit();
      }
    });
  }

}
