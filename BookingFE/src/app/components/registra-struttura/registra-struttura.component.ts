import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CameraService } from '../../services/camera.service';
import { ResidenzaService } from '../../services/residenza.service';
import { ResidenzaRequest } from '../../models/residenza-request';
import { CameraCustom } from '../../models/camera-custom';
import { forkJoin, finalize } from 'rxjs';

@Component({
  selector: 'app-registra-struttura',
  templateUrl: './registra-struttura.component.html',
  styleUrl: './registra-struttura.component.css',
})
export class RegistraStrutturaComponent {
  nuovaStrutturaForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private cameraService: CameraService,
    private residenzaService: ResidenzaService
  ) {
    this.nuovaStrutturaForm = this.fb.group({
      nome: ['', Validators.required],
      indirizzo: ['', Validators.required],
      camere: this.fb.array([this.createCamera()]),
    });
  }

  get camere(): FormArray {
    return this.nuovaStrutturaForm.get('camere') as FormArray;
  }

  createCamera(): FormGroup {
    return this.fb.group({
      postiLetto: ['', Validators.required],
      prezzoBase: ['', Validators.required],
      tipo: ['', Validators.required],
      numero: ['', Validators.required],
      infoCheckOut: ['', Validators.required],
    });
  }

  addCamera() {
    this.camere.push(this.createCamera());
  }

  removeCamera(index: number) {
    this.camere.removeAt(index);
  }

  onSubmit() {
    console.log(this.nuovaStrutturaForm.value);
    // Logica per inviare il form
    const residenza = new ResidenzaRequest(
      this.nuovaStrutturaForm.get('nome')?.value,
      this.nuovaStrutturaForm.get('indirizzo')?.value
    );

    this.residenzaService.add(residenza).subscribe({
      next: (data) => {
        const camereArray = this.nuovaStrutturaForm.get('camere')?.value;

        if (Array.isArray(camereArray)) {
          const cameraRequests = camereArray.map((element: any) => {
            let cameraAdd = new CameraCustom();
            cameraAdd.idResidenza = data.idResidenza;
            cameraAdd.postiLetto = element.postiLetto;
            cameraAdd.prezzoBase = element.prezzoBase;
            cameraAdd.numero = element.numero;
            cameraAdd.infoCheckOut = element.infoCheckOut;
            cameraAdd.tipo = element.tipo;

            return this.cameraService.addCamera(cameraAdd);
          });

          forkJoin(cameraRequests)
            .pipe(
              finalize(() => {
                // Operazioni da eseguire una volta che tutte le richieste HTTP sono completate
                console.log('Tutte le camere sono state aggiunte');
                this.nuovaStrutturaForm.reset();
              })
            )
            .subscribe({
              next: (responses) => {
                responses.forEach((data, index) => {
                  console.log(`Camera ${index + 1} aggiunta`, data);
                });
              },
              error: (e) => {
                console.error('Errore nella richiesta HTTP: ', e.message);
              },
            });
        }
      },
      error: (e) => {
        console.error('Errore nella richiesta HTTP: ', e.message);
      },
    });
  }
}
