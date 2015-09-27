/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dama;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;





public class GUI extends JFrame {
    
	private boolean ins=false;
        private boolean rim=false;
	private boolean userIsBlack;
	final private JMenuBar menuBar=new JMenuBar();
        final private JMenu gioco=new JMenu("Gioco");
        final  private JMenu about=new JMenu("About");
        private final JFrame in;        
        final private JMenuItem nuovaPartita=new JMenuItem("Nuova Partita");
        private final JMenuItem opzioni;
        private final JMenuItem undo=new JMenuItem("Annulla");
        private final JMenuItem carica=new JMenuItem("carica");
        private final JMenuItem salva =new JMenuItem("salva");
        final private JLabel a[][];
	final private GridBagConstraints c=new GridBagConstraints();
	final private JLabel turno;
	private Tavola t=new Tavola();	
	private Engine user=new Engine(0);
        private Engine pc=new Engine(1);
        final private JRadioButton bianco=new JRadioButton("Bianco");
        final private JRadioButton nero=new JRadioButton("Nero");   
        final private JButton ok=new JButton("ok");
        final private JButton ok2=new JButton("ok");
        int x;
        int y;
	private  ImageIcon cvn;
	private ImageIcon pb;
	private ImageIcon pn ;
	private ImageIcon psb;
	private ImageIcon psn ;
	private  ImageIcon cvb;
        private ImageIcon dn ;
	private ImageIcon dsb;
	private ImageIcon dsn ;
	private ImageIcon db;
	 private ImageIcon cvs ;
	private GestoreEventi ge=new GestoreEventi();
	private boolean partitaFinita=false;
        private final JFrame f;
        private JMenu debug=new JMenu("Debug");
        private JFrame fdebug;
        private JLabel label2=new JLabel("modalità gioco");
        private final JRadioButton biancoD=new JRadioButton("Bianco");
        private JRadioButton neroD=new JRadioButton("Nero");
        private JRadioButton pedina=new JRadioButton("pedina");
        private JRadioButton damone=new JRadioButton("damone");
        private JButton rpedina=new JButton("togliPedina");
        private JButton load=new JButton("load");
        private JButton save=new JButton("save");
        private JButton add=new JButton("add");
        private JButton okD=new JButton ("ok");
        private ButtonGroup pezzo=new ButtonGroup();
        private ButtonGroup colore=new ButtonGroup();
        private JButton pturno=new JButton("nextTurn");
        private JButton undoD=new JButton("undo");
        
	public GUI(String name){		
		super(name);
               
        cvn =new ImageIcon(this.getResource("imm/cvn.jpg"));
	pb=new ImageIcon(this.getResource("imm/pb.jpg"));
	 pn =new ImageIcon(this.getResource("imm/pn.jpg"));
	 psb=new ImageIcon(this.getResource("imm/psb.jpg"));
	 psn =new ImageIcon(this.getResource("imm/psn.jpg"));
	 cvb =new ImageIcon(this.getResource("imm/cvb.jpg"));
       dn =new ImageIcon(this.getResource("imm/dn.jpg"));
	dsb=new ImageIcon(this.getResource("imm/dsb.jpg"));
	 dsn =new ImageIcon(this.getResource("imm/dsn.jpg"));
	db =new ImageIcon(this.getResource("imm/db.jpg"));
	  cvs =new ImageIcon(this.getResource("imm/cvs.jpg")); 
         
        this.opzioni = new JMenuItem("Opzioni");
		
		
		this.setLayout(new GridBagLayout());
		this.a=new JLabel [8][8];
		for(int y=0;y<a.length;y++){
			for(int x=0;x<a[0].length;x++){
				c.gridx=x;
				c.gridy=y;
				
				a[y][x]=new JLabel();
				a[y][x].addMouseListener(ge);
				a[y][x].addMouseListener(new GestoreEventiDebug());
				this.add(a[y][x],c);
				
			}			
		}
			turno=new JLabel();
			c.gridx=0;
			c.gridy=8;
			c.gridwidth=8;
			this.add(turno,c);
                        
			this.in=new JFrame("Che colore scegli?");
                        this.in.setLayout(new FlowLayout());
                        ButtonGroup g=new ButtonGroup();
                        g.add(nero);
                        g.add(bianco);
                        this.in.add(bianco);
                        this.in.add(nero);
                        this.in.add(ok);
                        ok.addMouseListener(new GestoreEventi2());		
                        bianco.setSelected(true);
                        ok2.addMouseListener(new GestoreEventi2());
                        
                                           
                        menuBar.add(gioco);
                        gioco.add(nuovaPartita);
                        gioco.add(salva);
                        gioco.add(carica);
                        gioco.add(opzioni);
                        gioco.add(undo);
                        undo.addActionListener(new GestoreEventiMenu());
                        carica.addActionListener(new GestoreEventiMenu());
                        salva.addActionListener(new GestoreEventiMenu());
                        menuBar.add(about);
                        opzioni.addActionListener(new GestoreEventiMenu());
                        nuovaPartita.addActionListener(new GestoreEventiMenu());
                        about.addMouseListener(new GestoreEventi2());
                        menuBar.add(debug);
                        debug.addMouseListener(new GestoreEventi2());

                    
                        this.setJMenuBar(menuBar);
			this.setSizeF(400,400);
                        this.setResizable(false);
			this.setVisible(true);
                        in.setResizable(false);
                        
                        //finesra opzioni
                        f=new JFrame("Opzioni");
                                
                     JPanel ris=new JPanel(new GridLayout(4,1));
                     ris.setSize(100, 200);                        
                     ris.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Risoluzione"),BorderFactory.createEmptyBorder(5,5,5,5)));
                     JRadioButton r3=new JRadioButton("400x300");
                     JRadioButton r5=new JRadioButton("600x500");
                     JRadioButton r6=new JRadioButton("700x600");
                     JRadioButton r10=new JRadioButton("800x700");
                     final ButtonGroup r=new ButtonGroup();
                     r3.setSelected(true);
                     r.add(r3);r.add(r5);r.add(r6);r.add(r10);
                     ris.add(r3);ris.add(r5);ris.add(r6);ris.add(r10);
                     r3.setActionCommand("");
                     r5.setActionCommand("50");
                     r6.setActionCommand("60");
                     r10.setActionCommand("80");/*
                     GestoreEventiOpzioni g=new GestoreEventiOpzioni();
                     r3.addActionListener(g);
                     r5.addActionListener(g);
                     r6.addActionListener(g);
                     r10.addActionListener(g);     */                 

                     JPanel mang=new JPanel();
                     mang.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Opzioni mangiata"),BorderFactory.createEmptyBorder(5,5,5,5)));
                     final JRadioButton pmd=new JRadioButton("La pedina mangia il damone");
                     final JRadioButton pnmd=new JRadioButton("La pedina non mangia il damone");                 
                     JLabel lnp=new JLabel("N. di pedine equivalenti al valore di un damone=>");
                     final JSpinner np=new JSpinner(new SpinnerNumberModel(3,1,6,1));

                     pmd.setSelected(true);
                     final ButtonGroup mang1=new ButtonGroup();
                     mang1.add(pmd);mang1.add(pnmd);                   
                     /*pnmd.addActionListener(g);
                     pmd.addActionListener(g);*/
                     pnmd.setActionCommand("pnmd");
                     pmd.setActionCommand("pmd");
                     GroupLayout lm=new GroupLayout(mang);
                     mang.setLayout(lm);
                     lm.setAutoCreateGaps(true);
                     lm.setAutoCreateContainerGaps(true);
                     lm.setHorizontalGroup(lm.createSequentialGroup()
                             .addGroup(lm.createParallelGroup(GroupLayout.Alignment.LEADING)
                                     .addComponent(pmd)
                                     .addComponent(pnmd)
                                     .addComponent(lnp)
                             )                                 
                                     .addComponent(np)                                         

                     );
                     lm.setVerticalGroup(lm.createSequentialGroup()
                             .addComponent(pmd)
                             .addComponent(pnmd)
                             .addGroup(lm.createParallelGroup(GroupLayout.Alignment.LEADING)
                                     .addComponent(lnp)
                                     .addComponent(np)
                             )
                     );

                     JPanel patta=new JPanel();
                     patta.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Opzioni patta"),BorderFactory.createEmptyBorder(5,5,5,5)));
                     JRadioButton ap=new JRadioButton("attiva patta con mosse =>");
                     JRadioButton dp=new JRadioButton("disattiva patta per mosse");
                     ap.setActionCommand("ap");
                     dp.setActionCommand("dp");
                     JSpinner m=new JSpinner(new SpinnerNumberModel(3,3,10,1)); 
                     ap.setSelected(true);
                     final ButtonGroup gPatta=new ButtonGroup();
                     gPatta.add(ap);gPatta.add(dp);                         
                     GroupLayout gl=new GroupLayout(patta);
                     patta.setLayout(gl);
                     gl.setAutoCreateGaps(true);
                     gl.setAutoCreateContainerGaps(true);
                     gl.setHorizontalGroup(gl.createSequentialGroup()
                             .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                     .addComponent(dp)
                                     .addComponent(ap)
                             ).addComponent(m)
                     );
                     gl.setVerticalGroup(gl.createSequentialGroup()
                             .addComponent(dp)
                             .addGroup(gl.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                     .addComponent(ap)
                                     .addComponent(m)
                             )
                     );


                     JButton ok=new JButton("ok");                         
                     ok.addActionListener(new ActionListener(){
                         @Override
                         public void actionPerformed(ActionEvent e) {


                           if(pnmd.isSelected()){
                               user.getArbitro().setPedinaMangiaDamone(false);
                               pc.getArbitro().setPedinaMangiaDamone(false);

                           }
                           if(pmd.isSelected()){
                               user.getArbitro().setPedinaMangiaDamone(true);
                               pc.getArbitro().setPedinaMangiaDamone(true);

                           }


                            String c=r.getSelection().getActionCommand();
                            cvn=new ImageIcon(getResource("imm/cvn"+c+".jpg"));
                            pb=new ImageIcon(getResource("imm/pb"+c+".jpg"));
                            pn=new ImageIcon(getResource("imm/pn"+c+".jpg"));
                            psb=new ImageIcon(getResource("imm/psb"+c+".jpg"));
                            psn=new ImageIcon(getResource("imm/psn"+c+".jpg"));
                            cvb=new ImageIcon(getResource("imm/cvb"+c+".jpg"));
                            dn=new ImageIcon(getResource("imm/dn"+c+".jpg"));
                            dsn=new ImageIcon(getResource("imm/dsn"+c+".jpg"));
                            db=new ImageIcon(getResource("imm/db"+c+".jpg"));
                            dsb=new ImageIcon(getResource("imm/dsb"+c+".jpg"));
                            cvs=new ImageIcon(getResource("imm/cvs"+c+".jpg"));

                            int n;
                            if(!c.equals(""))
                                n=Integer.parseInt(c);
                            else
                                n=40;
                            setSizeF(n*10,n*10);

                            JComponent nn=np.getEditor();
                            if(nn instanceof JSpinner.DefaultEditor){
                                user.valoreDamone(Integer.parseInt(((JSpinner.DefaultEditor)nn).getTextField().getText()));
                                pc.valoreDamone(Integer.parseInt(((JSpinner.DefaultEditor)nn).getTextField().getText()));
                            }

                            c=gPatta.getSelection().getActionCommand();
                            if(c.equals("ap")){
                                JComponent pk=np.getEditor();
                                if(pk instanceof JSpinner.DefaultEditor){
                                user.setControlloPattaXMosse(Integer.parseInt(((JSpinner.DefaultEditor)pk).getTextField().getText()));
                                pc.setControlloPattaXMosse(Integer.parseInt(((JSpinner.DefaultEditor)pk).getTextField().getText()));
                                }

                            }
                             if(c.equals("dp")){
                                 user.setControlloPattaXMosse(0);
                                 pc.setControlloPattaXMosse(0);
                             }
                             aggiorna(t);
                             f.setVisible(false);
                         }

                 });                         


                     JButton annulla=new JButton("Annulla");
                     annulla.addActionListener(new ActionListener(){
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             f.setVisible(false);                                 
                         }                        
                 }); 

                     JPanel pan=new JPanel();
                     GroupLayout glf=new GroupLayout(pan);
                     pan.setLayout(glf);                     
                     glf.setAutoCreateGaps(true);
                     glf.setAutoCreateContainerGaps(true);

                     glf.setHorizontalGroup(glf.createParallelGroup(GroupLayout.Alignment.CENTER)
                             .addGroup(glf.createSequentialGroup()
                                     .addGroup(glf.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                               .addComponent(ris)                                         
                                    )
                                     .addGroup(glf.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(mang)
                                                .addComponent(patta)                                         
                                    ))
                             .addGroup(glf.createSequentialGroup()
                                     .addComponent(ok)
                                     .addComponent(annulla)
                             )

                     );
                     glf.setVerticalGroup(glf.createSequentialGroup()
                             .addGroup(glf.createParallelGroup(GroupLayout.Alignment.CENTER)
                                     .addComponent(ris)
                                     .addGroup(glf.createSequentialGroup()
                                             .addComponent(mang)
                                             .addComponent(patta)
                                     )
                             ).addGroup(glf.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                     .addComponent(ok)
                                     .addComponent(annulla)
                             )
                     );
                     f.add(pan);
                     f.setSize(490,330);
                     f.setResizable(false);
                     
                    this.fdebug=new JFrame("Debug");
                        GridLayout gr=new GridLayout(3,4);
                        fdebug.setLayout(gr);
                        pezzo.add(pedina);                        
                        pezzo.add(damone);                        
                        colore.add(biancoD);                        
                        colore.add(neroD);
                        biancoD.setSelected(true);
                        damone.setSelected(true);
                        fdebug.add(pedina);                        
                        fdebug.add(biancoD);
                        fdebug.add(add);
                        fdebug.add(load);
                        fdebug.add(damone);                        
                        fdebug.add(neroD);                        
                        fdebug.add(okD);
                        fdebug.add(save);
                        fdebug.add(label2);
                        fdebug.add(rpedina);
                       
                        fdebug.add(undoD);                       // f.add(load);
                       // f.add(save);
                        
                        add.addMouseListener(new GestoreEventiDebug ());
                        add.addMouseListener(ge);
                        okD.addMouseListener(new GestoreEventiDebug());
                        rpedina.addMouseListener(new GestoreEventiDebug ());
                        rpedina.addMouseListener(ge);
                        load.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        carica.doClick();
                    }
                            
                        });
                        save.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                       salva.doClick();
                    }
                            
                        });
                        undoD.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        undo.doClick(); //To change body of generated methods, choose Tools | Templates.
                    }
                            
                        });
                        fdebug.setSize(550,200);
                        
                     
                        this.addWindowListener(new WindowListener(){
                        
                       
                            
                    @Override
                    public void windowOpened(WindowEvent e) {
                        
                        if(new File(System.getProperty("user.dir")+"/ultimaPartita.txt").exists()){
                            int r=JOptionPane.showConfirmDialog(null,"continuare la partita precedente?","Dama",JOptionPane.YES_NO_OPTION);
                            //0 si 1 no
                            if(r==1)
                                nuovaPartita.doClick();
                            if(r==0){
                                    load(new File(System.getProperty("user.dir")+"/ultimaPartita.txt"));
                                    t.load(new File(System.getProperty("user.dir")+"/ultimaPartita.txt"));
                                    pc.loadH(new File(System.getProperty("user.dir")+"/ultimaPartita.txt"));
                             }    
                                aggiorna(t);

                            }else
                                nuovaPartita.doClick();
                                
                        
                    }
                    
                    @Override
                    public void windowClosing(WindowEvent e) {                       
                        if(!partitaFinita){
                            save(new File(System.getProperty("user.dir")+"/ultimaPartita.txt"));
                            t.save(new File(System.getProperty("user.dir")+"/ultimaPartita.txt"));
                            pc.saveH(new File(System.getProperty("user.dir")+"/ultimaPartita.txt"));
                        }
                        else
                            new File(System.getProperty("user.dir")+"/ultimaPartita.txt").delete();
                        System.exit(0);
                    
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {}
                    

                    @Override
                    public void windowIconified(WindowEvent e) {}

                    @Override
                    public void windowDeiconified(WindowEvent e) {}

                    @Override
                    public void windowActivated(WindowEvent e) { }

                    @Override
                    public void windowDeactivated(WindowEvent e) {}
                            
        });
		}
                private void setSizeF(int x,int y){
                    this.x=x;
                    this.y=y;
                }
		private void aggiorna(Tavola t) {
                    for(int y=0;y<8;y++){
                        for(int x=0;x<8;x++){
                            try{
                            if(t.isEmpty(new Cell(x,y))){
                                if(new Cell(x,y).isBianca())
                                    a[y][x].setIcon(cvb);	
                                if(new Cell(x,y).isNera())
                                   a[y][x].setIcon(cvn);
                            }else{
                                    
                                if(t.containsPedinaBianca(new Cell(x,y)))
                                        a[y][x].setIcon(pb);
                                if(t.containsPedinaNera(new Cell(x,y)))
                                        a[y][x].setIcon(pn);
                                if(t.containsDamoneBianco(new Cell(x,y)))
                                        a[y][x].setIcon(db);
                                if(t.containsDamoneNero(new Cell(x,y)))
                                        a[y][x].setIcon(dn);
					
                            }
                        }catch (CellaInesistenteException | CellaVuotaException e){}
                        }
                    }
                    super.setSize(x, y);
			
		}
		private void aggiornaSM(Cell c) {
            try {
                aggiorna(t);
                
                if(t.containsPedinaBianca(c))
                    a[c.getY()][c.getX()].setIcon(psb);
                if(t.containsDamoneBianco(c))
                    a[c.getY()][c.getX()].setIcon(dsb);
                for(Cell p:user.getPossiblyMoves(t,c))
                    a[p.getY()][p.getX()].setIcon(cvs);

               
            } catch (CellaVuotaException ex) { } 
            
			
            }
            public void save(File f) {
                try {
                    FileOutputStream fo=new FileOutputStream(f);
                    ObjectOutputStream oo=new ObjectOutputStream(fo); 
                    oo.writeBoolean(userIsBlack);
                    oo.close();
                } catch (FileNotFoundException ex) {} catch (IOException ex) { }
                
            }    
            public void load(File f) {
                try{
                    FileInputStream fi=new FileInputStream(f);
                    ObjectInputStream oi=new ObjectInputStream(fi);
                    boolean newUserIsBlack=oi.readBoolean();
                    oi.close();
                    if(userIsBlack!=newUserIsBlack)
                        this.scambiaPedine();
                    userIsBlack=newUserIsBlack;
                    } catch (FileNotFoundException ex) {        
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }        
                /*
            try {                
                Scanner s=new Scanner(f);
                String r=s.nextLine();                
                if(r.contains("N"))
                    utenteNero();
                
                if(r.contains("B"))
                    utenteBianco();
            } catch (FileNotFoundException ex) {}
                */
            
            
            }
            private void utenteNero(){
                if(!userIsBlack)
                    this.scambiaPedine();    
                userIsBlack=true;
                    

                    turno.setText("turno del nero");
            }
            private void utenteBianco(){
                if(userIsBlack)
                    this.scambiaPedine();
                this.userIsBlack=false;
                turno.setText("turno del bianco");
            }
            private void scambiaPedine(){
                ImageIcon temp;

                    temp=pb;
                    pb=pn;
                    pn=temp;

                    temp=db;
                    db=dn;
                    dn=temp;

                    temp=psb;
                    psb=psn;
                    psn=temp;

                    temp=dsb;
                    dsb=dsn;
                    dsn=temp;
            }
            public void finePartita(String message){
                this.partitaFinita=true;
                this.inibisciMovimenti();
                int r=JOptionPane.showConfirmDialog(null,"    "+message+"\n iniziare un'altra partita?", "Fine Partita", JOptionPane.YES_NO_OPTION);
                if(r==0){
                    nuovaPartita.doClick();
                }
                    
                
                
                
            }
            private void inibisciMovimenti(){
                for (JLabel[] a1 : a) 
                    for (int j = 0; j<a[0].length; j++) 
                        a1[j].removeMouseListener(ge);
                            
            }
            private void attivaMovimenti(){
                if(a[0][0].getMouseListeners().length==0)
                    for (JLabel[] a1 : a) 
                        for (int j = 0; j<a[0].length; j++) 
                            a1[j].addMouseListener(ge);
                    
            }
            public void iniziaPartita(){
               partitaFinita=false;
               t.inizio();
               user.clearHistory();
               if(nero.isSelected()){
                      utenteNero();         
                    t=pc.mossaPc(t);          
              }
               if (bianco.isSelected()){
                   utenteBianco();
               }
              
                attivaMovimenti();                 
                aggiorna(t);
            }
            public void showOpzioni(){
               f.setVisible(true);
                 
            }
            public final URL getResource(String rsc)   {
                // returns the ClassLoader object associated with this Class
                    ClassLoader cLoader = this.getClass().getClassLoader();
                    // input stream
                URL val = null;        
            try {
                val = cLoader.getResource(rsc).toURI().toURL();
               
            } catch (URISyntaxException | MalformedURLException ex) {  } 
                return val;
            }
		private class GestoreEventi implements MouseListener{
			
			
			
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!ins&&!rim){
                                    label2.setText("modalità gioco");
                                    if(arg0.getSource()==add){
                                        ins=true; 
                                        rim=false;
                                        label2.setText("modalità inserimento");
                                    }
                                    if(arg0.getSource()==rpedina){
                                        rim=true;
                                        ins=false;
                                        label2.setText("modalità rimozione");
                                    }
                                    
				for(int y=0;y<a.length;y++){
					for(int x=0;x<a[0].length;x++){
						if(arg0.getSource()==a[y][x]){
								Cell c=null;
                                                    try {
                                                        c = new Cell(x, y);
                                                    } catch (CellaInesistenteException ex) {                                               
                                                    }   
                                                        
							
							
							
							int r=user.receivedinput(t, c);
                                                        
                                                        if(r==2)
                                                           
                                                                aggiorna(t);
                                                        
							if(r==0){  
                                                            //
                                                            aggiornaSM(user.getArbitro().getSource());
                                                           
							}
                                                        
                                                          
							
                                              
                                                               
                                                        if(r==1){
                                                            boolean b=true;
                                                            aggiorna(t);
                                                            if(user.controlPatta(t)){
                                                                    finePartita("patta");
                                                                    b=false;
                                                             }
                                                            if(user.controlVictory(t)){
                                                                    finePartita("hai vinto");
                                                                     b=false;
                                                         } 
                                                              if(b){
                                                                t=pc.mossaPc(t);
                                                                aggiorna(t);
                                                                if(pc.controlPatta(t)){
                                                                finePartita("patta");                                                            
                                                                }
                                                                if(pc.controlVictory(t)){
                                                                    finePartita("hai perso");
                                                                 } 
                                                              }
                                                                
                                                                
                                                              }
                                                              
                                                  
                                                
							
						}
						
						
							
					}
				}
			}
                        }				
				
				
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
				
			

			
			
		}
                private class GestoreEventi2 implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent arg) {
           if(arg.getSource()==ok){               
              iniziaPartita();
              in.dispose();
                                }
           if(arg.getSource()==about){
              JOptionPane.showMessageDialog(null,"made by Stefano Gugole & Manuele Frigo", "About",JOptionPane.INFORMATION_MESSAGE);
           }
           if(arg.getSource()==debug){
               String pw=JOptionPane.showInputDialog(null, "password?");
               if(pw.equals("0000"))fdebug.setVisible(true);
           }
           
                   
           
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
             //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
             //To change body of generated methods, choose Tools | Templates.
        }
                    
                }
                private class GestoreEventiMenu implements ActionListener{

                    @Override
                    public void actionPerformed(ActionEvent arg) {
                       if(arg.getSource()==nuovaPartita){
                        
                        in.setSize(300,100);
                        in.setVisible(true);
                        

                     }
                     if(arg.getSource()==opzioni){
                         showOpzioni();
                     }
                     if(arg.getSource()==undo){
                        
                          if(!user.historyIsEmpty()){
                              t=user.lastMove();
                             
                              
                                  aggiorna(t);
                              
                          }
                     }
                    if(arg.getSource()==carica){
                        JFileChooser fc=new JFileChooser();                
                fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int ret=fc.showOpenDialog(carica);
                if(ret==JFileChooser.APPROVE_OPTION){
                        load(fc.getSelectedFile());
                        t.load(fc.getSelectedFile());
                        pc.loadH(fc.getSelectedFile());
                }
                            
                       
                         aggiorna(t);
                    
                
               
                    }
                    if(arg.getSource()==salva){
                        JFileChooser fc=new JFileChooser();                
                fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int ret=fc.showOpenDialog(salva);
                if(ret==JFileChooser.APPROVE_OPTION){
                        save(fc.getSelectedFile());
                        t.save(fc.getSelectedFile());
                        pc.saveH(fc.getSelectedFile());
                }    
                   
                    
                    }
                    }
                    
                }/*
                private class GestoreEventiOpzioni implements ActionListener{
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                    }
                    
                }*/
               private class GestoreEventiDebug implements MouseListener{
			
			
			
			
			@Override
			 public void mouseClicked(MouseEvent arg0) {
                                
				if(ins&&!rim){//preparazione
                                    label2.setText("modalità inserimento");
                                    if(arg0.getSource()==okD){
                                        ins=false;
                                        rim=false;
                                        label2.setText("modalità gioco");
                                    }
                                    if(arg0.getSource()==rpedina){
                                        ins=false;
                                        rim=true;
                                        label2.setText("modalità rimozione");
                                    }
                                    for(int y=0;y<8;y++){
					for(int x=0;x<8;x++){
                                            if(arg0.getSource()==a[y][x]){
                                                
                                               
                                                if(pedina.isSelected()&&nero.isSelected())
                                                    try{
                                                        t.insertPedinaNera(new Cell(x,y));
                                                    } catch(CellaNonVuotaException e){} catch (CellaInesistenteException ex) {
                                                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                if(pedina.isSelected()&&bianco.isSelected())
                                                    try{
                                                        t.insertPedinaBianca(new Cell(x,y));
                                                    } catch(CellaNonVuotaException e ){} catch (CellaInesistenteException ex) {
                                                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                               if(damone.isSelected()&&bianco.isSelected())
                                                    try{
                                                        t.insertPedinaBianca(new Cell(x,y));
                                                        t.promuoviPedina(new Cell(x,y));
                                                    } catch(CellaNonVuotaException d){} catch (CellaVuotaException ex) {
                                                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                                                } catch (CellaInesistenteException ex) {
                                                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                if(damone.isSelected()&&nero.isSelected())
                                                    try{
                                                        t.insertPedinaNera(new Cell(x,y));
                                                        t.promuoviPedina(new Cell(x,y));
                                                    } catch(CellaNonVuotaException e){} catch (CellaVuotaException ex) {
                                                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                                                } catch (CellaInesistenteException ex) {
                                                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }
                                        }
                                    }
                                      
                                        aggiorna(t);
                                   
                                }
                                if(!ins&&rim){
                                    label2.setText("modalità rimozione");
                                    if(arg0.getSource()==okD){
                                        ins=false;
                                        rim=false; 
                                        label2.setText("modalità gioco");
                                    }
                                    if(arg0.getSource()==add){
                                        ins=true;
                                        rim=false;
                                        label2.setText("modalità inserimento");
                                    }
                                    for(int y=0;y<a.length;y++){
					for(int x=0;x<a[0].length;x++){
                                            if(arg0.getSource()==a[y][x]){
                                                Cell c=null;
                                                    try {
                                                        c = new Cell(x, y);
                                                        t.removePezzo(c);
                                                    } catch (CellaInesistenteException ex) {                                               
                                                    } 
                                                   
                                                    
                                        }
                                        }
                                    }
                                   
                                        aggiorna(t);
                                    
                                }
                                
                        }

        @Override
        public void mousePressed(MouseEvent e) {
            //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //To change body of generated methods, choose Tools | Templates.
        }
			
		}
                
	}
	


