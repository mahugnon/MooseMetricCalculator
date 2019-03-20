Class {
	#name : #MMCMooseAssociationFormPresenter,
	#superclass : #MMCMooseAbstractQueryPresenter,
	#instVars : [
		'label',
		'selectButton',
		'classesPresenter',
		'queryMethodDropDown',
		'associationDropDown',
		'associationLabel',
		'methodLabel'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseAssociationFormPresenter class >> defaultSpec [
	| spec |
	spec := super defaultSpec.
	^ spec
		cellSpacing: 0.5;
		maxCellSize: 20;
		minCellSize: 10;
		newRow: [ :row | 
			row
				add: #associationLabel;
				add: #associationDropDown ];
		newRow: [ :r | 
			r
				add: #label;
				add: #selectButton;
				add: #queryMethodDropDown ];
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
MMCMooseAssociationFormPresenter >> classesPresenter [
	^ classesPresenter
]

{ #category : #'as yet unclassified' }
MMCMooseAssociationFormPresenter >> initAssociationLabel [
associationLabel:= self newLabel 
emphasis: #bold;
		label: 'Associations'
]

{ #category : #initialization }
MMCMooseAssociationFormPresenter >> initClassLabel [
	label := self newLabel
		emphasis: #bold;
		label: 'Class'
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
	self initClassLabel.
	self initSelectButton.
	self initClassesPresenter.
	self initQueryMethodDropDown.
	self initQueryAssociationDropDown.
	self initAssociationLabel .
	
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> label [
	^ label
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
MMCMooseAssociationFormPresenter >> selectButton [
	^ selectButton
]

{ #category : #updating }
MMCMooseAssociationFormPresenter >> update [
	classesPresenter items: context focus asOrderedCollection
]
