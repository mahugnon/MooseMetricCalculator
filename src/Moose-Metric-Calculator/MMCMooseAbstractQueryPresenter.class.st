Class {
	#name : #MMCMooseAbstractQueryPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'queriesDropDown',
		'queryValueInput',
		'queriesOperationsDropDown'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseAbstractQueryPresenter class >> defaultSpec [
	^ SpecTableLayout column
		cellSpacing: 20;
		add: #queriesDropDown;
		newRow: [ :r | r add: #queriesOperationsDropDown  ];
		add: #queryValueInput;
		yourself
]

{ #category : #initialization }
MMCMooseAbstractQueryPresenter >> initQueriesDropListPresenter [
	queriesDropDown := self instantiate: MMCDropListPresenter.
	queriesDropDown label: self name
]

{ #category : #initialization }
MMCMooseAbstractQueryPresenter >> initQueriesOperationsDropDown [
	queriesOperationsDropDown := self newDropList.
	queriesOperationsDropDown
		items: self operations;
		displayBlock: [ :item | item asString ]
]

{ #category : #initialization }
MMCMooseAbstractQueryPresenter >> initQueryValueInput [
	queryValueInput := self newTextInput.
	queryValueInput ghostText: self name , ' ' , 'value'
]

{ #category : #initialization }
MMCMooseAbstractQueryPresenter >> initializeWidgets [
	self initQueriesDropListPresenter.
	self initQueriesOperationsDropDown.
	self initQueryValueInput
]

{ #category : #accessing }
MMCMooseAbstractQueryPresenter >> name [
	^ self subclassResponsibility
]

{ #category : #accessing }
MMCMooseAbstractQueryPresenter >> operations [
	^ #(= < >) asOrderedCollection
]

{ #category : #accessing }
MMCMooseAbstractQueryPresenter >> queriesDropDown [
	^ queriesDropDown
]

{ #category : #accessing }
MMCMooseAbstractQueryPresenter >> queriesOperationsDropDown [
	^ queriesOperationsDropDown
]

{ #category : #accessing }
MMCMooseAbstractQueryPresenter >> queryValueInput [
	^ queryValueInput
]
