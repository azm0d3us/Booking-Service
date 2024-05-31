import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { Residenza } from '../../models/residenza';
import { Camera } from '../../models/camera';
import { ResidenzaService } from '../../services/residenza.service';
import { CameraService } from '../../services/camera.service';
import { PrenotazioneService } from '../../services/prenotazione.service';
import { PrenotazioneCustom } from '../../models/prenotazione-custom';
import { DateCustom } from '../../models/date-custom';
import { DateConverterService } from '../../services/date-converter.service';

@Component({
  selector: 'app-aggiungi-prenotazione',
  templateUrl: './aggiungi-prenotazione.component.html',
  styleUrl: './aggiungi-prenotazione.component.css'
})
export class AggiungiPrenotazioneComponent {

  adminForm: FormGroup;
  userAddForm: FormGroup;
  userNotFound: boolean = false;
  user: User = new User();
  residenze: Residenza[] = [];
  camere: Camera[] = [];
  idUser: number = 0;
  idCamera: number = 0;
  dataCheckIn: Date = new Date();
  dataCheckOut: Date = new Date();
  disponibile?: boolean;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private residenzaService: ResidenzaService,
    private cameraService: CameraService,
    private prenotazioneService: PrenotazioneService,
    private dateConverter: DateConverterService
  ) {

    this.adminForm = this.fb.group({
      nome: ['', Validators.required],
      cognome: ['', Validators.required],
      ddn: ['', Validators.required],
      codDoc: ['', Validators.required],
      cf: ['', Validators.required],
      residenza: [''],
      camera: ['', Validators.required],
      numPersone: ['', Validators.required],
      checkIn: ['', Validators.required],
      checkOut: ['', Validators.required]
    });

    this.userAddForm = this.fb.group({
      userNome: ['', Validators.required],
      userCognome: ['', Validators.required],
      userDdn: ['', Validators.required],
      userCodDoc: ['', Validators.required],
      userCf: ['', Validators.required]
    });

  }

  ngOnInit(): void {
    this.residenzaService.getAll().subscribe({
      next: (data) => {
        this.residenze = data;
      },
      error: (e) => {
        console.error("Errore nella richiesta HTTP: ", e.message);
      }
    })
  }

  onSearch() {
    const codiceFiscale = this.adminForm.get('cf')?.value;
    this.userService.getUserByDocumentCode(codiceFiscale).subscribe({
      next: (data) => {
        this.user = data;
        this.adminForm.patchValue({
          nome: data.nome,
          cognome: data.cognome,
          ddn: this.dateConverter.dateConverter(data.ddn),
          codDoc: data.codDoc,
          cf: data.cf
        });
        this.idUser = data.idUser;
        this.userNotFound = false;
      },
      error: (e) => {
        console.error("Errore richiesta HTTP: ", e.message);
        this.userNotFound = true;
        this.adminForm.patchValue({
          nome: '',
          cognome: '',
          ddn: '',
          codDoc: '',
          cf: ''
        })
      }
    });
  }

  // dateConverter(data: any) {
  //   let timestamp = data;
  //   let date = new Date(timestamp);

  //   let year = date.getFullYear();
  //   let month = (date.getMonth() + 1).toString().padStart(2, '0'); // getMonth() returns a zero-based value
  //   let day = date.getDate().toString().padStart(2, '0');

  //   let formattedDate = `${year}-${month}-${day}`;
  //   return formattedDate;
  // }

  onSubmit() {
    if(this.adminForm.valid) {
      this.prenotazioneService.prenota(new PrenotazioneCustom(
        this.idCamera,
        this.idUser,
        3,
        this.adminForm.get('checkIn')?.value,
        this.adminForm.get('checkOut')?.value
      )).subscribe({
        next: (data) => {
          console.log("Prenotazione ", data , " avvenuta con successo.");
        },
        error: (e) => {
          console.error("Errore nella richiesta HTTP: ", e.message);
        }
      })

    }
  }

  onUserAdd() {
    if(this.userAddForm.valid) {
      this.userService.adminAddUser({
        nome: this.userAddForm.get('userNome')?.value,
        cognome: this.userAddForm.get('userCognome')?.value,
        ddn: this.userAddForm.get('userDdn')?.value,
        docCod: this.userAddForm.get('userCodDoc')?.value,
        cf: this.userAddForm.get('userCf')?.value
      }).subscribe({
        next: (data) => {
          this.idUser = data.idUser;
        },
        error: (e) => {
          console.error("Errore nella richiesta HTTP: ", e.message);
        }
      })

      this.adminForm.patchValue({
        nome: this.userAddForm.get('userNome')?.value,
        cognome: this.userAddForm.get('userCognome')?.value,
        codDoc: this.userAddForm.get('userCodDoc')?.value,
        cf: this.userAddForm.get('userCf')?.value
      });

      this.userAddForm.reset();
      this.userNotFound = false;

    }
  }

  onResidenzaChange(event: any) {
    const residenzaId = event.target.value;
    if(residenzaId) {
      this.cameraService.getCameraByResidenza(residenzaId).subscribe({
      next: (data) => {
        this.camere = data;
      },
      error: (e) => {
        console.error('Errore caricamento camere: ', e.message);
      }
      });
    }
  }

  onCameraChange(event: any) {
    this.idCamera = Number(event.target.value);
  }

  checkDisponibility() {
    this.cameraService.getDisponibili(new DateCustom(
      this.adminForm.get('checkIn')?.value,
      this.adminForm.get('checkOut')?.value
    )).subscribe({
      next: (data) => {
        console.log(data);
        if (Array.isArray(data)) {
          this.disponibile = data.some(element => Number(element.idCamera) === Number(this.idCamera));
        }
        console.log("this.disponibile " + this.disponibile);
        console.log("this.idCamera " + this.idCamera)
      },
      error: (e) => {
        console.error("Errore nella richiesta HTTP: ", e.message);
      }
    })
  }

}
