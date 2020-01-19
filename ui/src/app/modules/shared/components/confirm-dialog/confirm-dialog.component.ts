import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css']
})
export class SharedConfirmDialogComponent implements OnInit {

  y = true;
  n = false;

  constructor(
    public dialogRef: MatDialogRef<SharedConfirmDialogComponent>
  ) {}

  ngOnInit() { }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
