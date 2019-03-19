"
I group navigation, property and association gui
"
Class {
	#name : #MMCMooseFormPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'formManager',
		'forms'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseFormPresenter class >> defaultSpec [
	^ SpecLayout composed
		add: #formManager;
		yourself
]

{ #category : #specs }
MMCMooseFormPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #accessing }
MMCMooseFormPresenter >> formManager [
	^ formManager
]

{ #category : #initialization }
MMCMooseFormPresenter >> initForms [
	forms := OrderedCollection new.
	forms add: (self instantiate: MMCMoosePropertyFormPresenter).
	forms add: (self instantiate: MMCMooseAssociationFormPresenter).
	forms add: (self instantiate: MMCMooseNavigationFormPresenter)
]

{ #category : #initialization }
MMCMooseFormPresenter >> initializePresenter [
	formManager
		whenTabSelected:
			[ :tab | self setActiveFormPresenter: tab ]
]

{ #category : #initialization }
MMCMooseFormPresenter >> initializeWidgets [
	self initForms.
	formManager := self newTabManager.
	formManager
		addTab: (self pageFor: forms first);
		addTab: (self pageFor: forms second);
		addTab: (self pageFor: forms third)
]

{ #category : #utilities }
MMCMooseFormPresenter >> pageFor: aForm [
	
	
	^ self newTab
		label: aForm name;
		closeable: false;
		presenter: aForm;
		yourself
]

{ #category : #initialization }
MMCMooseFormPresenter >> setActiveFormPresenter: tab [
"	^ context
		activeFormPresenter:
			(forms
				select: [ :each | each  = (tab retrievingBlockHolder value at: 1)])
				first"
]

{ #category : #subscription }
MMCMooseFormPresenter >> subscribeTo: aContext [
	super subscribeTo: aContext.
"	self setActiveFormPresenter: formManager tabs first."
	forms do: [ :each | each subscribeTo: aContext ]
]

{ #category : #subscription }
MMCMooseFormPresenter >> update [
	"context activeFormPresenter update"
]
