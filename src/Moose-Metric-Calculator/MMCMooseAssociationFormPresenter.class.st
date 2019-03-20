Class {
	#name : #MMCMooseAssociationFormPresenter,
	#superclass : #MMCMooseAbstractQueryPresenter,
	#instVars : [
		'selectButton',
		'classesPresenter',
		'queryMethodDropDown',
		'associationDropDown',
		'associationLabel',
		'classeNameLabel',
		'queryMethodLabel',
		'searchButton'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseAssociationFormPresenter class >> defaultSpec [
	| spec |
	spec := super defaultSpec.
	^ spec
		newColumn: [ :r | 
			r
				newRow: [ :row | row add: #queryMethodLabel ]
					height: self toolbarHeight;
				newRow: [ :row | row add: #queryMethodDropDown ]
					height: self toolbarHeight ];
		newColumn: [ :r | 
			r
				newRow: [ :row | row add: #classeNameLabel ]
					height: self toolbarHeight;
				newRow: [ :row | row add: #selectButton ]
					height: self toolbarHeight ];
		newColumn: [ :r | 
			r
				newRow: [ :row | row add: #associationLabel ]
					height: self toolbarHeight;
				newRow: [ :row | row add: #associationDropDown ]
					height: self toolbarHeight ];
		newRow: [ :row | row add: #searchButton  ]
			height: self toolbarHeight;
		yourself
]

{ #category : #examples }
MMCMooseAssociationFormPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> associationDropDown [
	^ associationDropDown
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> associationLabel [
	^ associationLabel
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> classeNameLabel [
	^ classeNameLabel
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> classesPresenter [
	^ classesPresenter
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initAssociationLabel [
associationLabel:= self newLabel 
emphasis: #bold;
		label: 'Associations'
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initClasseNameLabel [
	classeNameLabel := self newLabel
		emphasis: #bold;
		label: 'FamixClasse: '
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initClassesPresenter [
	classesPresenter := self newList
		sortingBlock: [ :a :b | a asString < b asString ]
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initQueryAssociationDropDown [
	associationDropDown := self newDropList
		items:
			((FAMIXAssociation allSubclasses
				select: [ :each | each ~= FAMIXTraitUsage ])
				sorted: [ :a :b | a name < b name ])
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initQueryMethodDropDown [
	queryMethodDropDown := self newDropList
		items: #(' queryLocal' 'query' 'queryAll' 'queryAllLocal');
		displayBlock: [ :a | a asString ]
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> initQueryMethodLabel [
queryMethodLabel  := self newLabel
		emphasis: #bold;
		label: 'Query Method: '
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initSearchButton [
	searchButton := self newButton.
	searchButton
		label: 'Search';
		icon: (self iconNamed: #glamorousSearch)
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initSelectButton [
	selectButton := self newButton label: '...'
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initializePresenter [
	selectButton action: [ self openClassesPresenter ]
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initializeWidgets [
	super initializeWidgets.
	self initClasseNameLabel.
	self initSelectButton.
	self initClassesPresenter.
	self initQueryMethodDropDown.
	self initQueryAssociationDropDown.
	self initQueryMethodLabel.
	self initAssociationLabel.
	self initSearchButton 
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> name [
	^ 'Association'
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> openClassesPresenter [
	classesPresenter openDialogWithSpec 
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> queryMethodDropDown [
	^ queryMethodDropDown
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> queryMethodLabel [
	^ queryMethodLabel
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> searchButton [
	^ searchButton
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> selectButton [
	^ selectButton
]

{ #category : #updating }
MMCMooseAssociationFormPresenter >> update [
	classesPresenter items: context focus asOrderedCollection
]
