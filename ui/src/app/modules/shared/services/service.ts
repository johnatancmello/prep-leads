import { MatSnackBar } from '@angular/material';

export class Service {

  constructor(
    protected snackBar: MatSnackBar,
  ) { }

  public openSnackBar(Class, data,  duration: number) {
    this.snackBar.openFromComponent(Class, {
      duration: duration * 1000, data
    });
  }

}
